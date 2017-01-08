package Property;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import Property.Review;

import org.apache.hadoop.io.Writable;

public class ReviewWritable implements Writable {
	private String text;
	private int stars;
    
    private boolean posLabel;
    
    public ReviewWritable() {}
    public ReviewWritable(Review review) {
        this.stars = review.stars;
        this.text = new String(review.text);
        this.posLabel = review.stars > 3;
    }
    
    public void SetText(String text) {
		this.text = text;
	}
    
    public void SetStar(int stars) {
		this.stars = stars;
		this.posLabel = stars > 3;
	}
    
    
    public void readFields(DataInput in) throws IOException {
        text = in.readUTF();  
        stars =  in.readInt();
        posLabel = in.readBoolean();
    }
    
    public void write(DataOutput out) throws IOException {
        out.writeUTF(text);
        out.writeInt(stars);
        out.writeBoolean(posLabel);
    }
}
