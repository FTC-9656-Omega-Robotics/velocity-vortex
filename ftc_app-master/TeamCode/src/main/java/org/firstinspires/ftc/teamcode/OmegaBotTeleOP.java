package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="OmegabotTeleop", group="VelocityVortex")
//@Disabled
public class OmegaBotTeleOP extends OpMode {

    public DcMotor leftDrive;        //before using a variable you have to declare it
    public DcMotor rightDrive; //Nidirena
    public DcMotor pickUp;
    public DcMotor fling;
    public DcMotor roll;
    public Servo capBall1;
    public Servo capBall2;
    public int loadPos;

    public float leftY;        //float is a data type for decimals
    public float rightY;

    public boolean isShooting = false; //Nidhir wrote this "lock" mechanic, so shouldn't he be responsible for motors burning out?
    public boolean isLoading = false; //your OG Nidhir Guggila wrote this code respect this man

    @Override
    public void init() {
        leftDrive = hardwareMap.dcMotor.get("left_drive");        //called left_drive in the config file
        leftDrive.setDirection(DcMotor.Direction.REVERSE); //prob will disable

        rightDrive = hardwareMap.dcMotor.get("right_drive");
        pickUp = hardwareMap.dcMotor.get("pickUp");
        roll = hardwareMap.dcMotor.get("roll");

        fling = hardwareMap.dcMotor.get("catapult");
        fling.setDirection(DcMotor.Direction.FORWARD);
        fling.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        loadPos = fling.getCurrentPosition();
        telemetry.addData("Load Position", loadPos);

        capBall1 = hardwareMap.servo.get("leftCapBall");
        capBall2 = hardwareMap.servo.get("rightCapBall");

    }

    @Override
    public void loop() {
        leftY = gamepad1.left_stick_y;            //joystick values range from -1 to 1 //changed to boolean dpad to see what'll happen
        rightY = gamepad1.right_stick_y;

        leftDrive.setPower(leftY);            //makes left motor move through gamepad joystick value
        rightDrive.setPower(rightY);

        if (gamepad2.right_bumper) {
            roll.setPower(-1);
            pickUp.setPower(1);
        } else {
            roll.setPower(0);
            pickUp.setPower(0);
        }


        //capball:
        if (gamepad2.left_stick_y < 0) {
                capBall1.setPosition(0.9);
                capBall2.setPosition(0.1);
            }
            else if (gamepad2.left_stick_y > 0){

                capBall1.setPosition(0.1);
                capBall2.setPosition(0.9);
            }



        /*how to shoot; motor initially at load position. press gamepad2.a for motor to turn, therefore reaching the section of the gear without teeth, and shooting the catapult. the motor never stops, and continues to turn, until it reaches loading position again, and stops.
        gamepad2.a=> load position */

      /*  if (!gamepad2.a) {
            if (fling.getCurrentPosition() == loadPos) {
                fling.setPower(0);
            }
        } else {
            fling.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fling.setPower(0.75);

            fling.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fling.setTargetPosition(loadPos+360);
            fling.setPower(1);
            loadPos+=360;
            telemetry.addData("Load Position", loadPos);
        }*/

        //   boolean isShooting=false; wtf lol
        if (gamepad2.a) {
            if (!isShooting) {
                isShooting = true;
                // fling.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                // fling.setPower(0.75);
                fling.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fling.setTargetPosition(loadPos + 1249);
                fling.setPower(0.5);
                loadPos += 1249;
                telemetry.addData("Load Position", loadPos);
            } else {
                if (Math.abs(fling.getCurrentPosition() - fling.getTargetPosition()) < 5) {
                    isShooting = false;
                    fling.setPower(0);
                }
            }
            // fling.setPower(0.5);
        } else {

        }
        if (gamepad2.b) {
            if (!isLoading) {
                isLoading = true;
                fling.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fling.setTargetPosition(loadPos + 189);
                fling.setPower(0.5);
                loadPos += 189; //originally 191
                //loadPos += 1440; // 1440 special units = 360 degrees
                telemetry.addData("Load Position", loadPos);
            } else {
                if (Math.abs(fling.getCurrentPosition() - fling.getTargetPosition()) < 5) { //originally 5
                    isLoading = false;
                    fling.setPower(0);
                }

            }
        }
    }
}
