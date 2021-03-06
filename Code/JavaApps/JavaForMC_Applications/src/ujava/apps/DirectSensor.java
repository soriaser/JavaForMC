package ujava.apps;

import java.mc.MicroApplication;
import java.mc.MicroApplicationListener;
import java.mc.ports.Port;
import java.mc.ports.PortConstants;
import java.mc.serialport.SerialPort;
import java.mc.serialport.SerialPortConstants;
import java.mc.timers.Timer;
import java.mc.timers.TimerConstants;

public class DirectSensor extends MicroApplication implements MicroApplicationListener {

    /**
     * Application state indicating next measure is Nx.
     */
    private final static byte STATE_MEASURE_NX = (byte) 0x00;

    /**
     * Application state indicating next measure is Nc1.
     */
    private final static byte STATE_MEASURE_NC1 = (byte) 0x01;

    /**
     * Application state indicating next measure is Nc2.
     */
    private final static byte STATE_MEASURE_NC2 = (byte) 0x02;

    /**
     * Java object representing microcontroller port resource.
     */
    private Port m_Port = null;

    /**
     * Java object representing microcontroller timer resource.
     */
    private Timer m_Timer = null;

    /**
     * Application variable indicating next measure to process.
     */
    private byte m_State = STATE_MEASURE_NX;

    /**
     * Auxiliar buffer
     */
    private byte[] m_AuxBuffer = {(byte) 0xFF, (byte) 0xFF};

    /**
     * Application starting method...
     */
    public void main() {
        // Store Microcontroller resource PORTB, that is going to be
        // modified during application to set pins to '0' or '1' in
        m_Port = Port.getPort(PortConstants.PORTB);
        // Store Microcontroller resource Timer1 used to measure time spent
        // to dischargue every capacitor conected to PORTB pins
        m_Timer = Timer.getTimer(TimerConstants.TIMER1);

        // Dischargue all capacitors connected to PORTB
        m_Port.setIO((byte) 0x0F);
        m_Port.setPins((byte) 0x00);
        // Prudential time to dischargue capacitors
        MicroApplication.Sleep((short) 30);

        // Application initially is registered to receive byte event
        // in order to keep listening on Serial Port pins
        MicroApplication.SetEvent(SerialPortConstants.EVENT_RECEIVED_BYTE);
        // Enable INT0 interrupt
        MicroApplication.SetEvent(PortConstants.EVENT_INTERRUPT_0);
    }

    /**
     * Method triggered when some event registered by this application happens.
     *
     * @param event Event identifier.
     */
    public void onEvent(byte event) {
        // True if application is measuring, false if not
        boolean measuring = true;

        // Just for timer value accuracy, Timer is disabled when some
        // event is received.
        m_Timer.disable();

        // If some byte is received through serial port, boolean m_StartMeasure
        // is set to true in order to indicate that measure can start
        if (SerialPortConstants.EVENT_RECEIVED_BYTE == event) {
            // Restart application state
            m_State = STATE_MEASURE_NX;
        }

        // If event INTO has been triggered, then it means that one dischargue
        // has been completed, then, store result on global array Measures
        if (PortConstants.EVENT_INTERRUPT_0 == event) {
            // Read timer value
            short timerValue = m_Timer.read();

            // Copy timer value to auxiliar buffer
            m_AuxBuffer[(short) 0] = (byte) (timerValue >> 8);
            m_AuxBuffer[(short) 1] = (byte) timerValue;
            // Send value
            SerialPort.Send(m_AuxBuffer, (short) 0, (short) 2);

            // If it is last state, then send Measures result and restart
            // m_State variable in order to start again if new byte is received
            if (m_State == STATE_MEASURE_NC2) {
                // Send end of line
                m_AuxBuffer[(short) 0] = (byte) '\n';
                // Restart application state
                m_State = STATE_MEASURE_NX;
                // Measuring finished
                measuring = false;
            } else {
                m_AuxBuffer[(short) 0] = (byte) ';';
            }
            
            // Send end of line or separator
            SerialPort.Send(m_AuxBuffer, (short) 0, (short) 1);

            // Next state
            m_State++;
        }

        // Application is measuring timings...
        if (measuring) {
            // Dischargue all capacitors connected to PORTB
            m_Port.setIO((byte) 0x0F);
            m_Port.setPins((byte) 0x10);

            // Prudential time to dischargue capacitors
            MicroApplication.Sleep((short) 30);

            // Clear Timer
            m_Timer.clear();

            // This variable will contain the configuration of PORTB pins
            // depending on current statre measure to do. The configuration
            // correspondg to pin direction
            byte ioPinsConfiguration = (byte) 0x00;

            // Set PORTB pins direction configuration
            if (STATE_MEASURE_NX == m_State) {
                ioPinsConfiguration = (byte) 0x1D;
            } else
            if (STATE_MEASURE_NC1 == m_State) {
                ioPinsConfiguration = (byte) 0x1B;
            } else
            if (STATE_MEASURE_NC2 == m_State) {
                ioPinsConfiguration = (byte) 0x17;
            }

            // Set PORTB directions configuration
            m_Port.setIO(ioPinsConfiguration);
            // Start Timer
            m_Timer.enable();
            // PORTB pin values (no directions)
            m_Port.setPins((byte) 0x00);
        }
        
    }

}
