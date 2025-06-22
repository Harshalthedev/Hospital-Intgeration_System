package Hospital_Integration.Hospital_System.model;

public class NotificationMessage {
    private String recipientId;
    private String content;
    
    public NotificationMessage() {}
    	
    public NotificationMessage(String recipientId, String content) {
        this.recipientId = recipientId;
        this.content = content;
    }
    
    // Getters & Setters

	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}    
}

