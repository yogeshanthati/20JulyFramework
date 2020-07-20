   


public static void robotType(Robot robot,String text ){

 StringSelection data = new StringSelection(text );
      Clipboard cb = Toolkit.getDefaultToolkit()
         .getSystemClipboard();
      cb.setContents(data, null);
robot.Delay(1000);
robot.KeyPress(KeyEvent.VK_CONTROL);
robot.KeyPress(KeyEvent.VK_CONTROL);
robot.Keyrelease(KeyEvent.VK_CONTROL);
robot.Keyrelease(KeyEvent.VK_CONTROL);
}