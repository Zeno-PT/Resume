package logic.game;

public class InsertAnimationPictureFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	// Throws massage when insert animation picture failed
	public InsertAnimationPictureFailedException(String message) {
		super();
		this.message = message;
	}

	// GETTER/SETTER
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
