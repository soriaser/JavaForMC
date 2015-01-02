/* 
 * File:   API.h
 * Author: Sergio Soria
 *
 * Created on 30 de diciembre de 2014, 11:49
 */

#ifndef API_H
#define	API_H

#ifdef	__cplusplus
extern "C" {
#endif

#define API_ID_MASK 0x7F

extern void Api_ExecuteNativeMethod(uint8_t id);

#ifdef	__cplusplus
}
#endif

#endif	/* API_H */
