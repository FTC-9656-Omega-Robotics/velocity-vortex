package com.qualcomm.ftcrobotcontroller.opmodes; //ignore the red yo

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "OmegabotAuto", group = "VelocityVortex")
//@Disable
public class OmegaBotAuto extends LinearOpMode {


    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor fling;

    public int loadPos;
    public boolean isShooting = false;

    private ElapsedTime     runtime = new ElapsedTime();

    /*
    @Override
    public void init(){
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        fling = hardwareMap.dcMotor.get("catapult");
    }
    */

    @Override
    public void runOpMode() {
        //startTime = System.nanoTime();
       // long deltaTime = startTime - lastTime;
     //   totalTime = totalTime + deltaTime;
        //if(totalTime >= 0l && totalTime <= 4000000000l) { //0-0.9 seconds

        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        fling = hardwareMap.dcMotor.get("catapult");
        loadPos = fling.getCurrentPosition();

        waitForStart();

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            leftDrive.setPower(1);
            rightDrive.setPower(1);
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4.0)) {
            if (!isShooting) {
                fling.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fling.setTargetPosition(loadPos + 1440);
                fling.setPower(-0.5);
                isShooting = true;
            /* if (Math.abs(fling.getCurrentPosition() - fling.getTargetPosition()) < 5) {
                fling.setPower(0);
            }
            */
            }
        }

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) { //5-8 seconds
            leftDrive.setPower(1);
            rightDrive.setPower(1);
            isShooting = false;
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
        /*else if (totalTime > 15000000000l){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
        lastTime = startTime;*/
    }
}

/*} else if(totalTime > 6000000001l && totalTime < 8000000000l) { //6-8 seconds
            leftDrive.setPower(1);
            rightDrive.setPower(1);
            isShooting = false;
            -> Nidhir's contribution */