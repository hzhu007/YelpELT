# YelpReviewELT

## Intro
Data source: [Kaggle Yelp Recruiting Competition](https://www.kaggle.com/c/yelp-recruiting)

Extract reviews from *yelp_training_set_review.json* on a cluster with Hadoop MapReduce and tag each review with its associated stars and a label describing it is a positive review (star > 3) or not.

## Run
The codes are written and complied in Eclipse.
Therefore, import the whole project if need further modification.
Hadoop Commands:
```
$ bin/hadoop fs -mkdir -p /user/hzhu/input
$ bin/hadoop fs -put yelp_training_set_review.json input
$ bin/hadoop jar ReviewExtraction.jar ReviewExtraction.ReviewExtraction input output
$ bin/hadoop fs -get output ./
$ bin/hadoop fs -cat output/*
$ bin/hadoop fs -rm -R output
```

## Sample input and output
Input format:
```
Review
{
  'type': 'review',
  'business_id': (encrypted business id),
  'user_id': (encrypted user id),
  'stars': (star rating),
  'text': (review text),
  'date': (date, formatted like '2012-03-14', %Y-%m-%d in strptime notation),
  'votes': {'useful': (count), 'funny': (count), 'cool': (count)}
}
```
Sample of yelp_training_set_review.json
```
{"votes": {"funny": 0, "useful": 5, "cool": 2}, "user_id": "rLtl8ZkDX5vH5nAx9C3q5Q", "review_id": "fWKvX83p0-ka4JS3dc6E5A", "stars": 5, "date": "2011-01-26", "text": "My wife took me here on my birthday for breakfast and it was excellent.  The weather was perfect which made sitting outside overlooking their grounds an absolute pleasure.  Our waitress was excellent and our food arrived quickly on the semi-busy Saturday morning.  It looked like the place fills up pretty quickly so the earlier you get here the better.\n\nDo yourself a favor and get their Bloody Mary.  It was phenomenal and simply the best I've ever had.  I'm pretty sure they only use ingredients from their garden and blend them fresh when you order it.  It was amazing.\n\nWhile EVERYTHING on the menu looks excellent, I had the white truffle scrambled eggs vegetable skillet and it was tasty and delicious.  It came with 2 pieces of their griddled bread with was amazing and it absolutely made the meal complete.  It was the best \"toast\" I've ever had.\n\nAnyway, I can't wait to go back!", "type": "review", "business_id": "9yKzy9PApeiPPOUJEtnvkg"}
{"votes": {"funny": 0, "useful": 0, "cool": 0}, "user_id": "0a2KyEL0d3Yb1V6aivbIuQ", "review_id": "IjZ33sJrzXqU-0X6U8NwyA", "stars": 5, "date": "2011-07-27", "text": "I have no idea why some people give bad reviews about this place. It goes to show you, you can please everyone. They are probably griping about something that their own fault...there are many people like that.\n\nIn any case, my friend and I arrived at about 5:50 PM this past Sunday. It was pretty crowded, more than I thought for a Sunday evening and thought we would have to wait forever to get a seat but they said we'll be seated when the girl comes back from seating someone else. We were seated at 5:52 and the waiter came and got our drink orders. Everyone was very pleasant from the host that seated us to the waiter to the server. The prices were very good as well. We placed our orders once we decided what we wanted at 6:02. We shared the baked spaghetti calzone and the small \"Here's The Beef\" pizza so we can both try them. The calzone was huge and we got the smallest one (personal) and got the small 11\" pizza. Both were awesome! My friend liked the pizza better and I liked the calzone better. The calzone does have a sweetish sauce but that's how I like my sauce!\n\nWe had to box part of the pizza to take it home and we were out the door by 6:42. So, everything was great and not like these bad reviewers. That goes to show you that  you have to try these things yourself because all these bad reviewers have some serious issues.", "type": "review", "business_id": "ZRJwVLyzEJq1VAihDhYiow"}
{"votes": {"funny": 0, "useful": 1, "cool": 0}, "user_id": "0hT2KtfLiobPvh6cDC8JQg", "review_id": "IESLBzqUCLdSzSqm0eCSxQ", "stars": 4, "date": "2012-06-14", "text": "love the gyro plate. Rice is so good and I also dig their candy selection :)", "type": "review", "business_id": "6oRAC4uyJCsJl1X0WZpVSA"}
```
Corresponding output
```
I have no idea why some people give bad reviews about this place. It goes to show you, you can please everyone. They are probably griping about something that their own fault...there are many people like that.\n\nIn any case, my friend and I arrived at about 5:50 PM this past Sunday. It was pretty crowded, more than I thought for a Sunday evening and thought we would have to wait forever to get a seat but they said we'll be seated when the girl comes back from seating someone else. We were seated at 5:52 and the waiter came and got our drink orders. Everyone was very pleasant from the host that seated us to the waiter to the server. The prices were very good as well. We placed our orders once we decided what we wanted at 6:02. We shared the baked spaghetti calzone and the small "Here's The Beef" pizza so we can both try them. The calzone was huge and we got the smallest one (personal) and got the small 11" pizza. Both were awesome! My friend liked the pizza better and I liked the calzone better. The calzone does have a sweetish sauce but that's how I like my sauce!\n\nWe had to box part of the pizza to take it home and we were out the door by 6:42. So, everything was great and not like these bad reviewers. That goes to show you that  you have to try these things yourself because all these bad reviewers have some serious issues.	stars: 5 positive: 1
My wife took me here on my birthday for breakfast and it was excellent.  The weather was perfect which made sitting outside overlooking their grounds an absolute pleasure.  Our waitress was excellent and our food arrived quickly on the semi-busy Saturday morning.  It looked like the place fills up pretty quickly so the earlier you get here the better.\n\nDo yourself a favor and get their Bloody Mary.  It was phenomenal and simply the best I've ever had.  I'm pretty sure they only use ingredients from their garden and blend them fresh when you order it.  It was amazing.\n\nWhile EVERYTHING on the menu looks excellent, I had the white truffle scrambled eggs vegetable skillet and it was tasty and delicious.  It came with 2 pieces of their griddled bread with was amazing and it absolutely made the meal complete.  It was the best "toast" I've ever had.\n\nAnyway, I can't wait to go back!	stars: 5 positive: 1
love the gyro plate. Rice is so good and I also dig their candy selection :)	stars: 4 positive: 1
```
## Asides
1. Virtual box and Ubuntu installation http://www.psychocats.net/ubuntu/virtualbox
2. Hadoop installation: https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/SingleCluster.html  
`$ jps # check if nodes are running` http://www.bogotobogo.com/Linux/hadoop2_running_on_ubuntu_single_node_cluster.php  
Permission denied problem when running _start-dfs.sh_  http://serverfault.com/questions/544917/pdsh-gives-error-rcmd-socket-permission-denied/775914
3. Share folder with VirtualBox http://askubuntu.com/a/679916 (only need to add user group then reboot)
4. Import library into eclipse http://stackoverflow.com/a/4962584
5. Work with Hadoop in Eclipse  
http://stackoverflow.com/questions/4058155/how-can-i-compile-a-java-program-in-eclipse-without-running-it  
http://stackoverflow.com/questions/15188042/where-are-hadoop-jar-files-in-hadoop-2/15202099#15202099
