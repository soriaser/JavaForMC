/* 
 * File:   SerialPort.h
 * Author: Sergio Soria
 *
 * Created on 12 de agosto de 2014, 17:06
 */

#ifndef SERIALPORT_H
#define	SERIALPORT_H

#ifdef	__cplusplus
extern "C" {
#endif

extern void SerialPort_Init(void);

extern void SerialPort_ISR(void);

extern void SerialPort_Send(unsigned char byte);

#ifdef	__cplusplus
}
#endif

#endif	/* SERIALPORT_H */
