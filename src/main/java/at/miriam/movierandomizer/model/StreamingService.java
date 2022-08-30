package at.miriam.movierandomizer.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StreamingService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	private long streamingID;
	private String name;

	public StreamingService() {
		super();
	}

	public StreamingService(long streamingID, String name) {
		super();
		this.setStreamingID(streamingID);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getStreamingID() {
		return streamingID;
	}

	public void setStreamingID(long streamingID) {
		this.streamingID = streamingID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StreamingService other = (StreamingService) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}

	
	
	
	
	
	
}
