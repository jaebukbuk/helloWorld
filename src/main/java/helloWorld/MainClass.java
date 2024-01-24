/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : MainClass.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package helloWorld;

/**
 * @ClassName    : MainClass
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.01.24
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.01.24       Hhome            최초생성
 * </pre>
 */
public class MainClass {

    public static void main(String[] args) {
        
        try {
            
            MouseRobot mouseRobot = new MouseRobot();
            mouseRobot.randomMouseMove(1);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
