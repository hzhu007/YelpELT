package ReviewExtraction;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;

import Property.Review;
import Property.ReviewWritable;


public class ReviewExtraction {
    public static class JsonMapper extends Mapper<Object, Text, Text, Text> {
        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            try {
                Gson gson = new Gson();
                String[] jsonUnits = value.toString().split("\\n"); // process line by line
                for (int i = 0; i < jsonUnits.length; i++) {
                    Review review = gson.fromJson(jsonUnits[i], Review.class);
                    if (review != null && review.text != null && !review.text.equals("")) {
                    	review.text = review.text.replace("\n", "\\n"); // for compact display
                    	// ReviewWritable writableReview = new ReviewWritable(review);
                		context.write(new Text(review.text), new Text("stars: " + review.stars +
                				" positive: " + (review.stars > 3 ? "1" : "0")));
                    }
                }

//                StringTokenizer jsonItr = new StringTokenizer(value.toString(), "\\n");
//                while (jsonItr.hasMoreTokens()) {
//                    String curr = jsonItr.nextToken();
//                	System.out.println(curr);
//                	Review review = gson.fromJson(curr, Review.class);
//                    if (review != null && review.text != null) {
//                    	review.text = review.text.replace("\n", "\\n");
//                    	context.write(new Text(review.text), new Text("stars: " + review.stars +
//                				" positive: " + (review.stars > 3 ? "1" : "0")));
//                    }
//                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        runJob(args[0], args[1]);
    }

    public static void runJob(String inputPath, String outputPath) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "YelpReviewELT");
        job.setJarByClass(ReviewExtraction.class);
        // specify all your types for both mapper and reducer
        // otherwise they will use the same setting
        job.setMapperClass(JsonMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(1);
//        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(inputPath));
        Path outPath = new Path(outputPath);
        FileOutputFormat.setOutputPath(job, outPath);
//        outPath.getFileSystem(conf).delete(outPath, true);

        job.waitForCompletion(true);
    }
}
