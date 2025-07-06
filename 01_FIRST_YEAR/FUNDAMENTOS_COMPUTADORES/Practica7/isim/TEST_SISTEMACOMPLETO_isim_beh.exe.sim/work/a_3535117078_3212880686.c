/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                       */
/*  \   \        Copyright (c) 2003-2009 Xilinx, Inc.                */
/*  /   /          All Right Reserved.                                 */
/* /---/   /\                                                         */
/* \   \  /  \                                                      */
/*  \___\/\___\                                                    */
/***********************************************************************/

/* This file is designed for use with ISim build 0x7708f090 */

#define XSI_HIDE_SYMBOL_SPEC true
#include "xsi.h"
#include <memory.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
static const char *ng0 = "C:/Users/pablo/OneDrive/Escritorio/P7_FC/PRACTICA_7/PRACTICA_7.vhd";
extern char *IEEE_P_2592010699;

unsigned char ieee_p_2592010699_sub_1744673427_503743352(char *, char *, unsigned int , unsigned int );


static void work_a_3535117078_3212880686_p_0(char *t0)
{
    char *t1;
    char *t2;
    unsigned char t3;
    unsigned char t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    int t10;
    int t11;
    int t12;
    int t13;
    char *t14;
    unsigned char t15;
    char *t16;
    char *t17;
    char *t18;
    char *t19;
    char *t20;

LAB0:    xsi_set_current_line(43, ng0);
    t1 = (t0 + 1672U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t4 = (t3 == (unsigned char)3);
    if (t4 != 0)
        goto LAB2;

LAB4:    t1 = (t0 + 1312U);
    t3 = ieee_p_2592010699_sub_1744673427_503743352(IEEE_P_2592010699, t1, 0U, 0U);
    if (t3 != 0)
        goto LAB5;

LAB6:
LAB3:    t1 = (t0 + 4288);
    *((int *)t1) = 1;

LAB1:    return;
LAB2:    xsi_set_current_line(43, ng0);
    t1 = (t0 + 2128U);
    t5 = *((char **)t1);
    t1 = (t0 + 4400);
    t6 = (t1 + 56U);
    t7 = *((char **)t6);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    memcpy(t9, t5, 2U);
    xsi_driver_first_trans_fast(t1);
    goto LAB3;

LAB5:    xsi_set_current_line(45, ng0);
    t2 = (t0 + 1832U);
    t5 = *((char **)t2);
    t2 = (t0 + 2128U);
    t6 = *((char **)t2);
    t10 = xsi_mem_cmp(t6, t5, 2U);
    if (t10 == 1)
        goto LAB8;

LAB13:    t2 = (t0 + 2248U);
    t7 = *((char **)t2);
    t11 = xsi_mem_cmp(t7, t5, 2U);
    if (t11 == 1)
        goto LAB9;

LAB14:    t2 = (t0 + 2368U);
    t8 = *((char **)t2);
    t12 = xsi_mem_cmp(t8, t5, 2U);
    if (t12 == 1)
        goto LAB10;

LAB15:    t2 = (t0 + 2488U);
    t9 = *((char **)t2);
    t13 = xsi_mem_cmp(t9, t5, 2U);
    if (t13 == 1)
        goto LAB11;

LAB16:
LAB12:
LAB7:    goto LAB3;

LAB8:    xsi_set_current_line(47, ng0);
    t2 = (t0 + 1032U);
    t14 = *((char **)t2);
    t4 = *((unsigned char *)t14);
    t15 = (t4 == (unsigned char)3);
    if (t15 != 0)
        goto LAB18;

LAB20:
LAB19:    goto LAB7;

LAB9:    xsi_set_current_line(48, ng0);
    t1 = (t0 + 1032U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t4 = (t3 == (unsigned char)3);
    if (t4 != 0)
        goto LAB21;

LAB23:    xsi_set_current_line(48, ng0);
    t1 = (t0 + 2128U);
    t2 = *((char **)t1);
    t1 = (t0 + 4400);
    t5 = (t1 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 2U);
    xsi_driver_first_trans_fast(t1);

LAB22:    goto LAB7;

LAB10:    xsi_set_current_line(49, ng0);
    t1 = (t0 + 1032U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t4 = (t3 == (unsigned char)2);
    if (t4 != 0)
        goto LAB24;

LAB26:
LAB25:    goto LAB7;

LAB11:    xsi_set_current_line(50, ng0);
    t1 = (t0 + 1032U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t4 = (t3 == (unsigned char)2);
    if (t4 != 0)
        goto LAB27;

LAB29:    xsi_set_current_line(50, ng0);
    t1 = (t0 + 2248U);
    t2 = *((char **)t1);
    t1 = (t0 + 4400);
    t5 = (t1 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 2U);
    xsi_driver_first_trans_fast(t1);

LAB28:    goto LAB7;

LAB17:;
LAB18:    xsi_set_current_line(47, ng0);
    t2 = (t0 + 2248U);
    t16 = *((char **)t2);
    t2 = (t0 + 4400);
    t17 = (t2 + 56U);
    t18 = *((char **)t17);
    t19 = (t18 + 56U);
    t20 = *((char **)t19);
    memcpy(t20, t16, 2U);
    xsi_driver_first_trans_fast(t2);
    goto LAB19;

LAB21:    xsi_set_current_line(48, ng0);
    t1 = (t0 + 2368U);
    t5 = *((char **)t1);
    t1 = (t0 + 4400);
    t6 = (t1 + 56U);
    t7 = *((char **)t6);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    memcpy(t9, t5, 2U);
    xsi_driver_first_trans_fast(t1);
    goto LAB22;

LAB24:    xsi_set_current_line(49, ng0);
    t1 = (t0 + 2488U);
    t5 = *((char **)t1);
    t1 = (t0 + 4400);
    t6 = (t1 + 56U);
    t7 = *((char **)t6);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    memcpy(t9, t5, 2U);
    xsi_driver_first_trans_fast(t1);
    goto LAB25;

LAB27:    xsi_set_current_line(50, ng0);
    t1 = (t0 + 2128U);
    t5 = *((char **)t1);
    t1 = (t0 + 4400);
    t6 = (t1 + 56U);
    t7 = *((char **)t6);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    memcpy(t9, t5, 2U);
    xsi_driver_first_trans_fast(t1);
    goto LAB28;

}

static void work_a_3535117078_3212880686_p_1(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;

LAB0:    xsi_set_current_line(55, ng0);

LAB3:    t1 = (t0 + 1832U);
    t2 = *((char **)t1);
    t1 = (t0 + 4464);
    t3 = (t1 + 56U);
    t4 = *((char **)t3);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    memcpy(t6, t2, 2U);
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t7 = (t0 + 4304);
    *((int *)t7) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_3535117078_3212880686_p_2(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    int t4;
    char *t5;
    int t6;
    char *t7;
    int t8;
    char *t9;
    int t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;

LAB0:    xsi_set_current_line(59, ng0);
    t1 = (t0 + 1832U);
    t2 = *((char **)t1);
    t1 = (t0 + 2128U);
    t3 = *((char **)t1);
    t4 = xsi_mem_cmp(t3, t2, 2U);
    if (t4 == 1)
        goto LAB3;

LAB8:    t1 = (t0 + 2248U);
    t5 = *((char **)t1);
    t6 = xsi_mem_cmp(t5, t2, 2U);
    if (t6 == 1)
        goto LAB4;

LAB9:    t1 = (t0 + 2368U);
    t7 = *((char **)t1);
    t8 = xsi_mem_cmp(t7, t2, 2U);
    if (t8 == 1)
        goto LAB5;

LAB10:    t1 = (t0 + 2488U);
    t9 = *((char **)t1);
    t10 = xsi_mem_cmp(t9, t2, 2U);
    if (t10 == 1)
        goto LAB6;

LAB11:
LAB7:    xsi_set_current_line(64, ng0);
    t1 = (t0 + 4528);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t7 = *((char **)t5);
    *((unsigned char *)t7) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t1 = (t0 + 4320);
    *((int *)t1) = 1;

LAB1:    return;
LAB3:    xsi_set_current_line(60, ng0);
    t1 = (t0 + 4528);
    t11 = (t1 + 56U);
    t12 = *((char **)t11);
    t13 = (t12 + 56U);
    t14 = *((char **)t13);
    *((unsigned char *)t14) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB4:    xsi_set_current_line(61, ng0);
    t1 = (t0 + 4528);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t7 = *((char **)t5);
    *((unsigned char *)t7) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB5:    xsi_set_current_line(62, ng0);
    t1 = (t0 + 4528);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t7 = *((char **)t5);
    *((unsigned char *)t7) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB6:    xsi_set_current_line(63, ng0);
    t1 = (t0 + 4528);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t7 = *((char **)t5);
    *((unsigned char *)t7) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB12:;
}


void ieee_p_2592010699_sub_3130575329_503743352();

extern void work_a_3535117078_3212880686_init()
{
	static char *pe[] = {(void *)work_a_3535117078_3212880686_p_0,(void *)work_a_3535117078_3212880686_p_1,(void *)work_a_3535117078_3212880686_p_2};
	xsi_register_didat("work_a_3535117078_3212880686", "isim/TEST_SISTEMACOMPLETO_isim_beh.exe.sim/work/a_3535117078_3212880686.didat");
	xsi_register_executes(pe);
	xsi_register_resolution_function(1, 2, (void *)ieee_p_2592010699_sub_3130575329_503743352, 4);
}
