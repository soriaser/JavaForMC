/* 
 * File:   InitializeArray.h
 * Author: Sergio Soria
 *
 * Created on 13 de septiembre de 2014, 17:05
 */

#ifndef STATIC_INIT_VALUE
#error "Please define 'STATIC_INIT_VALUE' before including this file!"
#endif

#ifndef STATIC_INIT_COUNT
#error "Please define 'STATIC_INIT_COUNT' before including this file!"
#endif

#define INIT_1 STATIC_INIT_VALUE,
#if STATIC_INIT_COUNT & 1
INIT_1
#endif
#define INIT_2 INIT_1 INIT_1
#if STATIC_INIT_COUNT & 2
INIT_2
#endif
#define INIT_4 INIT_2 INIT_2
#if STATIC_INIT_COUNT & 4
INIT_4
#endif
#define INIT_8 INIT_4 INIT_4
#if STATIC_INIT_COUNT & 8
INIT_8
#endif
#define INIT_16 INIT_8 INIT_8
#if STATIC_INIT_COUNT & 16
INIT_16
#endif
#define INIT_32 INIT_16 INIT_16
#if STATIC_INIT_COUNT & 32
INIT_32
#endif
#define INIT_64 INIT_32 INIT_32
#if STATIC_INIT_COUNT & 64
INIT_64
#endif
#define INIT_128 INIT_64 INIT_64
#if STATIC_INIT_COUNT & 128
INIT_128
#endif
#define INIT_256 INIT_128 INIT_128
#if STATIC_INIT_COUNT & 256
INIT_256
#endif
#define INIT_512 INIT_256 INIT_256
#if STATIC_INIT_COUNT & 512
INIT_512
#endif
#define INIT_1024 INIT_512 INIT_512
#if STATIC_INIT_COUNT & 1024
INIT_1024
#endif
#define INIT_2048 INIT_1024 INIT_1024
#if STATIC_INIT_COUNT & 2048
INIT_2048
#endif
#define INIT_4096 INIT_2048 INIT_2048
#if STATIC_INIT_COUNT & 4096
INIT_4096
#endif
#define INIT_8192 INIT_4096 INIT_4096
#if STATIC_INIT_COUNT & 8192
INIT_8192
#endif
#define INIT_16384 INIT_8192 INIT_8192
#if STATIC_INIT_COUNT & 16384
INIT_16384
#endif
#define INIT_32768 INIT_16384 INIT_16384
#if STATIC_INIT_COUNT & 32768
INIT_32768
#endif

#if STATIC_INIT_COUNT >= 65536
#error "Please add more init blocks to this list!"
#endif
