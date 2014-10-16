/* 
 * File:   Stack.h
 * Author: Sergio Soria
 *
 * Created on 15 de octubre de 2014, 13:39
 */

#ifndef STACK_H
#define	STACK_H

#ifdef	__cplusplus
extern "C" {
#endif

extern uint16_t *Stack_Pointer;
extern uint16_t *Stack_CurrentPointer;
extern uint16_t *Stack_BasePointer;

extern void Stack_Init(void);
extern void Stack_Push(uint16_t value);

#ifdef	__cplusplus
}
#endif

#endif	/* STACK_H */
