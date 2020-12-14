package vo;

public class Review {
	private int number;
	private int lecture_num;
	private String contents;
	private String title;
	private int review_num;
	
	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getLecture_num() {
		return lecture_num;
	}

	public void setLecture_num(int lecture_num) {
		this.lecture_num = lecture_num;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
