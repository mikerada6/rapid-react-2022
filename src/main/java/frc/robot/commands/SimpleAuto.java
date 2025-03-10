package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

public
class SimpleAuto extends SequentialCommandGroup {
  public
  SimpleAuto(Shooter shooter, Conveyor conveyor, Drivetrain m_robotDrive) {
    addCommands(new Shoot(shooter,
                          ShooterConstants.LOW_GOAL_SPEED).withTimeout(2.0),
                new ParallelCommandGroup(new Shoot(shooter,
                                                   ShooterConstants.LOW_GOAL_SPEED).withTimeout(2.5),
                                         new ConveyorUp(conveyor).withTimeout(1.0)),
                new ParallelCommandGroup(new DriveAuto(-.75,
                                                       m_robotDrive).withTimeout(2.25),
                                         new ConveyorUp(conveyor).withTimeout(3)),
                new ParallelCommandGroup(new ConveyorDown(conveyor,
                                                          0).withTimeout(.25),
                                         new DriveAuto(.75,
                                                       m_robotDrive).withTimeout(.9),
                                         new WarmUpShooter(shooter,
                                                           ShooterConstants.HIGH_GOAL_SPEED).withTimeout(3.0)),
                new ParallelCommandGroup(new Shoot(shooter,
                                                   ShooterConstants.HIGH_GOAL_SPEED).withTimeout(3.0),
                                         new ConveyorUp(conveyor).withTimeout(3.0)));
  }

}
