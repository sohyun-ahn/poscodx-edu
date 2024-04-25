package chapter03;

public class SongTest {

	public static void main(String[] args) {
//		Song song = new Song(); 
		Song song01 = new Song("좋은날", "아이유", "real", "이민수", 3, 2010);
		
//		song01.setTitle("좋은날");
//		song01.setArtist("아이유");
//		song01.setAlbum("real");
//		song01.setComposer("이민수");
//		song01.setTrack(3);
//		song01.setYear(2010);
		song01.show();
		
		Song song02 = new Song("Ditto", "New Jeans");
		
		song02.show(); // 오버로딩
	}

}
