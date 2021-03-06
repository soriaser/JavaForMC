/* 
 * File:   Platform_PIC18F4520.h
 * Author: Sergio Soria
 *
 * Created on 05 de septiembre de 2014, 17:13
 */

#ifndef PLATFORM_PIC18F4520_H
#define	PLATFORM_PIC18F4520_H

#ifdef	__cplusplus
extern "C" {
#endif

#include <xc.h>

#pragma config CP0      = OFF
#pragma config CP1      = OFF
#pragma config CP2      = OFF
#pragma config CP3      = OFF
#pragma config CPB      = OFF
#pragma config CPD      = OFF
#pragma config WRT0     = OFF
#pragma config WRT1     = OFF
#pragma config WRT2     = OFF
#pragma config WRT3     = OFF
#pragma config WRTB     = OFF
#pragma config WRTC     = OFF
#pragma config WRTD     = OFF
#pragma config EBTR0    = OFF
#pragma config EBTR1    = OFF
#pragma config EBTR2    = OFF
#pragma config EBTR3    = OFF
#pragma config EBTRB    = OFF
#pragma config PBADEN   = OFF
#pragma config BOREN    = OFF
#pragma config OSC      = HS

#define _XTAL_FREQ 4000000
#define BAUDRATE 2400
#define JAVACLASS_MAX_SIZE_DATA     400
#define JVM_MAX_SIZE_HEAP           200

#define INT0_RISING_EDGE
#define USE_RAM_ONLY
//#define USE_STDIO_FOR_SERIAL_PORT
#define STORE_TIMER_IF_INT0

#ifdef	__cplusplus
}
#endif

#endif	/* PLATFORM_PIC18F4520_H */