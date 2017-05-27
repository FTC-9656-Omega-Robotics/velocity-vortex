package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "CataTeleOp", group = "VelocityVortex")
//@Disabled
public class CataTeleOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftDrive, rightDrive, pickUp, fling, roll = null;

    public int loadPos;

    public boolean readyToShoot = false;

    @Override
    public void init() {
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        pickUp = hardwareMap.dcMotor.get("pickUp");
        roll = hardwareMap.dcMotor.get("roll");

        fling = hardwareMap.dcMotor.get("catapult");
        fling.setDirection(DcMotor.Direction.REVERSE);
        fling.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        loadPos = fling.getCurrentPosition();
        telemetry.addData("Load Position", loadPos);

    }

    @Override
    public void loop() {
        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(gamepad1.right_stick_y);

        roll.setPower(gamepad2.right_bumper ? 1:0);
        pickUp.setPower(gamepad2.right_bumper ? 1:0);
        //Load
        if (gamepad2.a) {
            runtime.reset();
            while (runtime.seconds() < 0.0025) {
                fling.setPower(0.9); //change to 0.5 if need be
                loadPos += fling.getCurrentPosition();
                telemetry.addData("Load Position", loadPos);
            }
            fling.setPower(0);
            readyToShoot = true;
        }
        //Fire
        if (gamepad2.b) {
            runtime.reset();
            while ((runtime.seconds() < 0.4336805556) && (readyToShoot)) {
                fling.setPower(0.5);
                loadPos += fling.getCurrentPosition();
                telemetry.addData("Load Position", loadPos);
            }
            fling.setPower(0);
            readyToShoot = false;
        }
    }
}