package org.improving.tag;

import org.improving.tag.commands.DanceCommand;
import org.improving.tag.commands.InventoryCommand;
import org.improving.tag.commands.JumpCommand;
import org.improving.tag.commands.LookCommand;

import java.util.Date;
import java.util.Scanner;

public class Game {
    private Date startTime;
    private Date endTime;
    
    public Date getStartTime() {
        return startTime;
    }

    private void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    private void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.setStartTime(new Date());

        boolean loop = true;
        while (loop) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            LookCommand lCmd = new LookCommand();
            DanceCommand dCmd = new DanceCommand();
            InventoryCommand iCmd = new InventoryCommand();
            JumpCommand jCmd = new JumpCommand();

            if (lCmd.isValid(input)) {
                lCmd.execute(input);
            } else if (iCmd.isValid(input)) {
                iCmd.execute(input);
            } else if (dCmd.isValid(input)) {
                dCmd.execute(input);
            } else if (jCmd.isValid(input)) {
                jCmd.execute(input);
            } else if (input.equals("exit")) {
                System.out.println("Goodbye.");
                loop = false;
            } else {
                System.out.println("Huh? I don't understand.");
            }
        }
        this.setEndTime(new Date());
    }


}
