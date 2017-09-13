{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf830
{\fonttbl\f0\fmodern\fcharset0 Courier;}
{\colortbl;\red255\green255\blue255;\red0\green0\blue0;}
{\*\expandedcolortbl;;\cssrgb\c0\c0\c0;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720\partightenfactor0

\f0\fs26 \cf0 \expnd0\expndtw0\kerning0
package com.qualcomm.ftcrobotcontroller.opmodes; //ignore the red yo\
\
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;\
import com.qualcomm.robotcore.eventloop.opmode.OpMode;\
import com.qualcomm.robotcore.hardware.DcMotor;\
import com.qualcomm.robotcore.eventloop.opmode.Disabled;\
import com.qualcomm.robotcore.hardware.DcMotorSimple;\
import com.qualcomm.robotcore.hardware.Servo;\
import com.qualcomm.robotcore.util.ElapsedTime;\
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;\
\
@Autonomous(name = "OmegaBotAuto", group = "VelocityVortex")\
//@Disable\
public class OmegaBotAuto extends LinearOpMode \{\
\
    //declare variables\
    private DcMotor leftDrive;\
    private DcMotor rightDrive;\
    private DcMotor pickUp;\
    private DcMotor roll;\
    private DcMotor fling;\
    private Servo capBall1;\
    private Servo capBall2;\
\
    public int loadPos;\
    //public boolean isShooting = false;\
\
    private ElapsedTime     runtime = new ElapsedTime();\
\
\
\
    @Override\
    public void runOpMode() \{\
        leftDrive = hardwareMap.dcMotor.get("left_drive");\
        rightDrive = hardwareMap.dcMotor.get("right_drive");\
        rightDrive.setDirection(DcMotor.Direction.REVERSE);\
\
        pickUp = hardwareMap.dcMotor.get("pickUp");\
        roll = hardwareMap.dcMotor.get("roll");\
        roll.setDirection(DcMotor.Direction.REVERSE);\
\
        fling = hardwareMap.dcMotor.get("catapult");\
        fling.setDirection(DcMotor.Direction.REVERSE);\
        fling.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);\
\
        loadPos = fling.getCurrentPosition();\
        telemetry.addData("Load Position", loadPos);\
        capBall1 = hardwareMap.servo.get("leftCapBall");\
        capBall2 = hardwareMap.servo.get("rightCapBall");\
\
\
\
        waitForStart();\
\
        runtime.reset();\
        while (opModeIsActive() && (runtime.seconds() < 1.5)) \{\
            leftDrive.setPower(1);\
            rightDrive.setPower(1);\
        \}\
        leftDrive.setPower(0);\
        rightDrive.setPower(0);\
        runtime.reset();\
        while (opModeIsActive() && (runtime.seconds() < 0.6)) \{\
            rightDrive.setPower(1);\
        \}\
        leftDrive.setPower(0);\
        rightDrive.setPower(0);\
        runtime.reset();\
        while (opModeIsActive() && (runtime.seconds() < 1.3)) \{ //mod1\
            leftDrive.setPower(1);\
            rightDrive.setPower(1);\
        \}\
        leftDrive.setPower(0);\
        rightDrive.setPower(0);\
        runtime.reset();\
        while (opModeIsActive() && (runtime.seconds() < 0.9)) \{ //5-8 seconds\
            leftDrive.setPower(1);\
        \}\
        leftDrive.setPower(0);\
        rightDrive.setPower(0);\
        runtime.reset();\
        while (opModeIsActive() && (runtime.seconds() < 1)) \{\
            leftDrive.setPower(1);\
            rightDrive.setPower(1);\
        \}\
        leftDrive.setPower(0);\
        rightDrive.setPower(0);\
    \}\
\}}