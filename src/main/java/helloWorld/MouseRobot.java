/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : mouseRobot.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package helloWorld;

import java.awt.AWTException;
import java.awt.Robot;

/**
 * @ClassName    : mouseRobot
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.01.24
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.01.24       Hhome            최초생성
 * </pre>
 */
public class MouseRobot {

    private final Robot robot;
    
    MouseRobot() throws AWTException{
        robot = new Robot(); 
    }
    
    public void randomMouseMove(int seq) throws InterruptedException {
        
        int x = 0;
        int y = 0;
        if(seq <= 3 ) seq = 3;
        int milSeq = seq * 1000;
        
        while(true) { // 종료할 때 까지 무한 반복
            
            x = (int) (Math.random() * 1920); // 1920*1080 해상도 내의 x,y 값을 랜덤하게 생성
            y = (int) (Math.random() * 1080);
            
            robot.mouseMove(x, y); // x,y 좌표로 이동

            Thread.sleep(milSeq);  // 10초 대기 후 다음 좌표로 이동
        }
    }
    
    public static void main(String[] args) {
        
        try {
            
            System.out.println("MouseRobot 에서 실행");
            MouseRobot mouseRobot = new MouseRobot();
            mouseRobot.randomMouseMove(1);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
