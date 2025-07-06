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
static const char *ng0 = "C:/Users/Usuario/OneDrive/Escritorio/Practicas FC/Practica5FC/FC_P5.vhd";
extern char *IEEE_P_2592010699;
extern char *IEEE_P_3620187407;

int ieee_p_3620187407_sub_514432868_3965413181(char *, char *, char *);


static void work_a_3916267816_3212880686_p_0(char *t0)
{
    char t6[16];
    char t11[16];
    char t16[16];
    char *t1;
    char *t2;
    unsigned char t3;
    char *t4;
    unsigned char t5;
    char *t7;
    char *t8;
    char *t9;
    unsigned char t10;
    char *t12;
    char *t13;
    char *t14;
    unsigned char t15;
    char *t17;
    unsigned int t18;
    unsigned int t19;
    unsigned int t20;
    unsigned char t21;
    char *t22;
    char *t23;
    char *t24;
    char *t25;
    char *t26;
    char *t27;

LAB0:    xsi_set_current_line(54, ng0);

LAB3:    t1 = (t0 + 1032U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t1 = (t0 + 1192U);
    t4 = *((char **)t1);
    t5 = *((unsigned char *)t4);
    t7 = ((IEEE_P_2592010699) + 4024);
    t1 = xsi_base_array_concat(t1, t6, t7, (char)99, t3, (char)99, t5, (char)101);
    t8 = (t0 + 1352U);
    t9 = *((char **)t8);
    t10 = *((unsigned char *)t9);
    t12 = ((IEEE_P_2592010699) + 4024);
    t8 = xsi_base_array_concat(t8, t11, t12, (char)97, t1, t6, (char)99, t10, (char)101);
    t13 = (t0 + 1512U);
    t14 = *((char **)t13);
    t15 = *((unsigned char *)t14);
    t17 = ((IEEE_P_2592010699) + 4024);
    t13 = xsi_base_array_concat(t13, t16, t17, (char)97, t8, t11, (char)99, t15, (char)101);
    t18 = (1U + 1U);
    t19 = (t18 + 1U);
    t20 = (t19 + 1U);
    t21 = (4U != t20);
    if (t21 == 1)
        goto LAB5;

LAB6:    t22 = (t0 + 5352);
    t23 = (t22 + 56U);
    t24 = *((char **)t23);
    t25 = (t24 + 56U);
    t26 = *((char **)t25);
    memcpy(t26, t13, 4U);
    xsi_driver_first_trans_fast(t22);

LAB2:    t27 = (t0 + 5192);
    *((int *)t27) = 1;

LAB1:    return;
LAB4:    goto LAB2;

LAB5:    xsi_size_not_matching(4U, t20, 0);
    goto LAB6;

}

static void work_a_3916267816_3212880686_p_1(char *t0)
{
    char t5[16];
    char t19[16];
    char t32[16];
    char t45[16];
    char t58[16];
    char t71[16];
    char t84[16];
    char *t1;
    char *t2;
    char *t3;
    char *t6;
    char *t7;
    int t8;
    unsigned int t9;
    unsigned char t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;
    char *t17;
    char *t20;
    char *t21;
    int t22;
    unsigned char t23;
    char *t24;
    char *t25;
    char *t26;
    char *t27;
    char *t28;
    char *t29;
    char *t30;
    char *t33;
    char *t34;
    int t35;
    unsigned char t36;
    char *t37;
    char *t38;
    char *t39;
    char *t40;
    char *t41;
    char *t42;
    char *t43;
    char *t46;
    char *t47;
    int t48;
    unsigned char t49;
    char *t50;
    char *t51;
    char *t52;
    char *t53;
    char *t54;
    char *t55;
    char *t56;
    char *t59;
    char *t60;
    int t61;
    unsigned char t62;
    char *t63;
    char *t64;
    char *t65;
    char *t66;
    char *t67;
    char *t68;
    char *t69;
    char *t72;
    char *t73;
    int t74;
    unsigned char t75;
    char *t76;
    char *t77;
    char *t78;
    char *t79;
    char *t80;
    char *t81;
    char *t82;
    char *t85;
    char *t86;
    int t87;
    unsigned char t88;
    char *t89;
    char *t90;
    char *t91;
    char *t92;
    char *t93;
    char *t94;
    char *t95;
    char *t96;
    char *t97;
    char *t98;

LAB0:    xsi_set_current_line(55, ng0);
    t1 = (t0 + 2312U);
    t2 = *((char **)t1);
    t1 = (t0 + 8480U);
    t3 = (t0 + 8532);
    t6 = (t5 + 0U);
    t7 = (t6 + 0U);
    *((int *)t7) = 0;
    t7 = (t6 + 4U);
    *((int *)t7) = 3;
    t7 = (t6 + 8U);
    *((int *)t7) = 1;
    t8 = (3 - 0);
    t9 = (t8 * 1);
    t9 = (t9 + 1);
    t7 = (t6 + 12U);
    *((unsigned int *)t7) = t9;
    t10 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t2, t1, t3, t5);
    if (t10 != 0)
        goto LAB3;

LAB4:    t15 = (t0 + 2312U);
    t16 = *((char **)t15);
    t15 = (t0 + 8480U);
    t17 = (t0 + 8536);
    t20 = (t19 + 0U);
    t21 = (t20 + 0U);
    *((int *)t21) = 0;
    t21 = (t20 + 4U);
    *((int *)t21) = 3;
    t21 = (t20 + 8U);
    *((int *)t21) = 1;
    t22 = (3 - 0);
    t9 = (t22 * 1);
    t9 = (t9 + 1);
    t21 = (t20 + 12U);
    *((unsigned int *)t21) = t9;
    t23 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t16, t15, t17, t19);
    if (t23 != 0)
        goto LAB5;

LAB6:    t28 = (t0 + 2312U);
    t29 = *((char **)t28);
    t28 = (t0 + 8480U);
    t30 = (t0 + 8540);
    t33 = (t32 + 0U);
    t34 = (t33 + 0U);
    *((int *)t34) = 0;
    t34 = (t33 + 4U);
    *((int *)t34) = 3;
    t34 = (t33 + 8U);
    *((int *)t34) = 1;
    t35 = (3 - 0);
    t9 = (t35 * 1);
    t9 = (t9 + 1);
    t34 = (t33 + 12U);
    *((unsigned int *)t34) = t9;
    t36 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t29, t28, t30, t32);
    if (t36 != 0)
        goto LAB7;

LAB8:    t41 = (t0 + 2312U);
    t42 = *((char **)t41);
    t41 = (t0 + 8480U);
    t43 = (t0 + 8544);
    t46 = (t45 + 0U);
    t47 = (t46 + 0U);
    *((int *)t47) = 0;
    t47 = (t46 + 4U);
    *((int *)t47) = 3;
    t47 = (t46 + 8U);
    *((int *)t47) = 1;
    t48 = (3 - 0);
    t9 = (t48 * 1);
    t9 = (t9 + 1);
    t47 = (t46 + 12U);
    *((unsigned int *)t47) = t9;
    t49 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t42, t41, t43, t45);
    if (t49 != 0)
        goto LAB9;

LAB10:    t54 = (t0 + 2312U);
    t55 = *((char **)t54);
    t54 = (t0 + 8480U);
    t56 = (t0 + 8548);
    t59 = (t58 + 0U);
    t60 = (t59 + 0U);
    *((int *)t60) = 0;
    t60 = (t59 + 4U);
    *((int *)t60) = 3;
    t60 = (t59 + 8U);
    *((int *)t60) = 1;
    t61 = (3 - 0);
    t9 = (t61 * 1);
    t9 = (t9 + 1);
    t60 = (t59 + 12U);
    *((unsigned int *)t60) = t9;
    t62 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t55, t54, t56, t58);
    if (t62 != 0)
        goto LAB11;

LAB12:    t67 = (t0 + 2312U);
    t68 = *((char **)t67);
    t67 = (t0 + 8480U);
    t69 = (t0 + 8552);
    t72 = (t71 + 0U);
    t73 = (t72 + 0U);
    *((int *)t73) = 0;
    t73 = (t72 + 4U);
    *((int *)t73) = 3;
    t73 = (t72 + 8U);
    *((int *)t73) = 1;
    t74 = (3 - 0);
    t9 = (t74 * 1);
    t9 = (t9 + 1);
    t73 = (t72 + 12U);
    *((unsigned int *)t73) = t9;
    t75 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t68, t67, t69, t71);
    if (t75 != 0)
        goto LAB13;

LAB14:    t80 = (t0 + 2312U);
    t81 = *((char **)t80);
    t80 = (t0 + 8480U);
    t82 = (t0 + 8556);
    t85 = (t84 + 0U);
    t86 = (t85 + 0U);
    *((int *)t86) = 0;
    t86 = (t85 + 4U);
    *((int *)t86) = 3;
    t86 = (t85 + 8U);
    *((int *)t86) = 1;
    t87 = (3 - 0);
    t9 = (t87 * 1);
    t9 = (t9 + 1);
    t86 = (t85 + 12U);
    *((unsigned int *)t86) = t9;
    t88 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t81, t80, t82, t84);
    if (t88 != 0)
        goto LAB15;

LAB16:
LAB17:    t93 = (t0 + 5416);
    t94 = (t93 + 56U);
    t95 = *((char **)t94);
    t96 = (t95 + 56U);
    t97 = *((char **)t96);
    *((unsigned char *)t97) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t93);

LAB2:    t98 = (t0 + 5208);
    *((int *)t98) = 1;

LAB1:    return;
LAB3:    t7 = (t0 + 5416);
    t11 = (t7 + 56U);
    t12 = *((char **)t11);
    t13 = (t12 + 56U);
    t14 = *((char **)t13);
    *((unsigned char *)t14) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t7);
    goto LAB2;

LAB5:    t21 = (t0 + 5416);
    t24 = (t21 + 56U);
    t25 = *((char **)t24);
    t26 = (t25 + 56U);
    t27 = *((char **)t26);
    *((unsigned char *)t27) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t21);
    goto LAB2;

LAB7:    t34 = (t0 + 5416);
    t37 = (t34 + 56U);
    t38 = *((char **)t37);
    t39 = (t38 + 56U);
    t40 = *((char **)t39);
    *((unsigned char *)t40) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t34);
    goto LAB2;

LAB9:    t47 = (t0 + 5416);
    t50 = (t47 + 56U);
    t51 = *((char **)t50);
    t52 = (t51 + 56U);
    t53 = *((char **)t52);
    *((unsigned char *)t53) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t47);
    goto LAB2;

LAB11:    t60 = (t0 + 5416);
    t63 = (t60 + 56U);
    t64 = *((char **)t63);
    t65 = (t64 + 56U);
    t66 = *((char **)t65);
    *((unsigned char *)t66) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t60);
    goto LAB2;

LAB13:    t73 = (t0 + 5416);
    t76 = (t73 + 56U);
    t77 = *((char **)t76);
    t78 = (t77 + 56U);
    t79 = *((char **)t78);
    *((unsigned char *)t79) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t73);
    goto LAB2;

LAB15:    t86 = (t0 + 5416);
    t89 = (t86 + 56U);
    t90 = *((char **)t89);
    t91 = (t90 + 56U);
    t92 = *((char **)t91);
    *((unsigned char *)t92) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t86);
    goto LAB2;

LAB18:    goto LAB2;

}

static void work_a_3916267816_3212880686_p_2(char *t0)
{
    char *t1;
    char *t2;
    int t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    char *t9;

LAB0:    xsi_set_current_line(64, ng0);

LAB3:    t1 = (t0 + 2312U);
    t2 = *((char **)t1);
    t1 = (t0 + 8480U);
    t3 = ieee_p_3620187407_sub_514432868_3965413181(IEEE_P_3620187407, t2, t1);
    t4 = (t0 + 5480);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    *((int *)t8) = t3;
    xsi_driver_first_trans_fast(t4);

LAB2:    t9 = (t0 + 5224);
    *((int *)t9) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_3916267816_3212880686_p_3(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    int t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;

LAB0:    t1 = (t0 + 4376U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(66, ng0);
    t2 = (t0 + 2472U);
    t3 = *((char **)t2);
    t4 = *((int *)t3);
    if (t4 == 3)
        goto LAB5;

LAB7:    if (t4 == 6)
        goto LAB5;

LAB8:    if (t4 == 9)
        goto LAB5;

LAB9:    if (t4 == 12)
        goto LAB5;

LAB10:    if (t4 == 15)
        goto LAB5;

LAB11:
LAB6:    xsi_set_current_line(67, ng0);
    t2 = (t0 + 5544);
    t3 = (t2 + 56U);
    t5 = *((char **)t3);
    t6 = (t5 + 56U);
    t7 = *((char **)t6);
    *((unsigned char *)t7) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t2);

LAB4:    xsi_set_current_line(66, ng0);

LAB15:    t2 = (t0 + 5240);
    *((int *)t2) = 1;
    *((char **)t1) = &&LAB16;

LAB1:    return;
LAB5:    xsi_set_current_line(67, ng0);
    t2 = (t0 + 5544);
    t5 = (t2 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    *((unsigned char *)t8) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t2);
    goto LAB4;

LAB12:;
LAB13:    t3 = (t0 + 5240);
    *((int *)t3) = 0;
    goto LAB2;

LAB14:    goto LAB13;

LAB16:    goto LAB14;

}

static void work_a_3916267816_3212880686_p_4(char *t0)
{
    char *t1;
    char *t2;
    int t3;
    unsigned char t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;

LAB0:    xsi_set_current_line(71, ng0);
    t1 = (t0 + 2472U);
    t2 = *((char **)t1);
    t3 = *((int *)t2);
    t4 = (t3 == 5);
    if (t4 != 0)
        goto LAB2;

LAB4:    t1 = (t0 + 2472U);
    t2 = *((char **)t1);
    t3 = *((int *)t2);
    t4 = (t3 == 10);
    if (t4 != 0)
        goto LAB5;

LAB6:    t1 = (t0 + 2472U);
    t2 = *((char **)t1);
    t3 = *((int *)t2);
    t4 = (t3 == 15);
    if (t4 != 0)
        goto LAB7;

LAB8:    xsi_set_current_line(74, ng0);
    t1 = (t0 + 5608);
    t2 = (t1 + 56U);
    t5 = *((char **)t2);
    t6 = (t5 + 56U);
    t7 = *((char **)t6);
    *((unsigned char *)t7) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t1);

LAB3:    t1 = (t0 + 5256);
    *((int *)t1) = 1;

LAB1:    return;
LAB2:    xsi_set_current_line(71, ng0);
    t1 = (t0 + 5608);
    t5 = (t1 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    *((unsigned char *)t8) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB3;

LAB5:    xsi_set_current_line(72, ng0);
    t1 = (t0 + 5608);
    t5 = (t1 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    *((unsigned char *)t8) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB3;

LAB7:    xsi_set_current_line(73, ng0);
    t1 = (t0 + 5608);
    t5 = (t1 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    *((unsigned char *)t8) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB3;

}

static void work_a_3916267816_3212880686_p_5(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    int t4;
    char *t5;
    char *t6;
    int t7;
    char *t8;
    int t10;
    char *t11;
    int t13;
    char *t14;
    int t16;
    char *t17;
    int t19;
    char *t20;
    int t22;
    char *t23;
    char *t24;
    char *t25;
    char *t26;
    char *t27;

LAB0:    xsi_set_current_line(80, ng0);
    t1 = (t0 + 2312U);
    t2 = *((char **)t1);
    t1 = (t0 + 8560);
    t4 = xsi_mem_cmp(t1, t2, 4U);
    if (t4 == 1)
        goto LAB3;

LAB11:    t5 = (t0 + 8564);
    t7 = xsi_mem_cmp(t5, t2, 4U);
    if (t7 == 1)
        goto LAB4;

LAB12:    t8 = (t0 + 8568);
    t10 = xsi_mem_cmp(t8, t2, 4U);
    if (t10 == 1)
        goto LAB5;

LAB13:    t11 = (t0 + 8572);
    t13 = xsi_mem_cmp(t11, t2, 4U);
    if (t13 == 1)
        goto LAB6;

LAB14:    t14 = (t0 + 8576);
    t16 = xsi_mem_cmp(t14, t2, 4U);
    if (t16 == 1)
        goto LAB7;

LAB15:    t17 = (t0 + 8580);
    t19 = xsi_mem_cmp(t17, t2, 4U);
    if (t19 == 1)
        goto LAB8;

LAB16:    t20 = (t0 + 8584);
    t22 = xsi_mem_cmp(t20, t2, 4U);
    if (t22 == 1)
        goto LAB9;

LAB17:
LAB10:    xsi_set_current_line(88, ng0);
    t1 = (t0 + 5672);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t1 = (t0 + 5272);
    *((int *)t1) = 1;

LAB1:    return;
LAB3:    xsi_set_current_line(81, ng0);
    t23 = (t0 + 5672);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    t26 = (t25 + 56U);
    t27 = *((char **)t26);
    *((unsigned char *)t27) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t23);
    goto LAB2;

LAB4:    xsi_set_current_line(82, ng0);
    t1 = (t0 + 5672);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB5:    xsi_set_current_line(83, ng0);
    t1 = (t0 + 5672);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB6:    xsi_set_current_line(84, ng0);
    t1 = (t0 + 5672);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB7:    xsi_set_current_line(85, ng0);
    t1 = (t0 + 5672);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB8:    xsi_set_current_line(86, ng0);
    t1 = (t0 + 5672);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB9:    xsi_set_current_line(87, ng0);
    t1 = (t0 + 5672);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t5 = (t3 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t1);
    goto LAB2;

LAB18:;
}


extern void work_a_3916267816_3212880686_init()
{
	static char *pe[] = {(void *)work_a_3916267816_3212880686_p_0,(void *)work_a_3916267816_3212880686_p_1,(void *)work_a_3916267816_3212880686_p_2,(void *)work_a_3916267816_3212880686_p_3,(void *)work_a_3916267816_3212880686_p_4,(void *)work_a_3916267816_3212880686_p_5};
	xsi_register_didat("work_a_3916267816_3212880686", "isim/FC_P5_isim_beh.exe.sim/work/a_3916267816_3212880686.didat");
	xsi_register_executes(pe);
}
