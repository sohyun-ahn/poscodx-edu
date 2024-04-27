package prob2;

public class SmartPhone extends MusicPhone {
	public void execute ( String function ) {
	      if (function.equals("음악")) {
	    	  downloadAndPlayMusic();
	          return;
	      } else if(function.equals("앱")) {
	    	  executeApp();
	      } else {
	    	  super.execute(function);
	      }
	      
	}
	
	private void downloadAndPlayMusic() {
		System.out.println("다운로드해서 음악재생");
	}
	
	private void executeApp() {
		System.out.println("앱실행");
	}
}
