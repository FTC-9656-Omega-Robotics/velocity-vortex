package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="OmegaMotorTester", group="Testers")
//@Disabled
public class OmegaMotorTester extends OpMode {

    public DcMotor leftDrive;        //before using a variable you have to declare it

    public float leftY;        //float is a data type for decimals

    @Override
    public void init() {
        leftDrive = hardwareMap.dcMotor.get("left_drive");        //called left_drive in the config file
        leftDrive.setDirection(DcMotor.Direction.REVERSE); //prob will disable
    }

    @Override
    public void loop() {
        leftY = gamepad1.left_stick_y;            //joystick values range from -1 to 1 //changed to boolean dpad to see what'll happen
        leftDrive.setPower(leftY);
    }
}
