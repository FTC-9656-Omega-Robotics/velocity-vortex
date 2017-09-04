// package com.qualcomm.ftcrobotcontroller.opmodes; //ignore the red yo
package org.firstinspires.ftc.teamcode; //revert to top if needed

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "OmegaBotAuto", group = "VelocityVortex")
//@Disable
public class OmegaBotAuto extends LinearOpMode {


    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor pickUp;
    private DcMotor roll;
    private DcMotor fling;

    public int loadPos;
    public boolean isShooting = false;

    private ElapsedTime     runtime = new ElapsedTime();



    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        pickUp = hardwareMap.dcMotor.get("pickUp");
        roll = hardwareMap.dcMotor.get("roll");

        fling = hardwareMap.dcMotor.get("catapult");
        fling.setDirection(DcMotor.Direction.FORWARD);
        fling.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        loadPos = fling.getCurrentPosition();
        telemetry.addData("Load Position", loadPos);


        waitForStart();

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            leftDrive.setPower(1);
            rightDrive.setPower(1);
            roll.setPower(-1);
            pickUp.setPower(1);
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        runtime.reset();
        if (opModeIsActive() && (runtime.seconds() < 2) && (!isShooting)) {
                fling.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fling.setTargetPosition(loadPos + 1440);
                fling.setPower(0.05);
                isShooting = true;
        }
        if (isShooting) {
            fling.setPower(0);
        }

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) { //5-8 seconds
            leftDrive.setPower(1);
            rightDrive.setPower(1);
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
