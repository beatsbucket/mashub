/*
 * Copyright 2015 The Mashub Project
 *
 * The Mashub Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.beatsbucket.mashub.kitchen.ingredient;

import java.net.MalformedURLException;
import java.net.URL;

import com.beatsbucket.mashub.kitchen.ingredient.twitter.TimelineAction;
import com.beatsbucket.mashub.kitchen.ingredient.twitter.TweetAction;
import junit.framework.Assert;
import org.junit.Test;

import com.beatsbucket.mashub.channel.OAuth1Channel;
import com.beatsbucket.mashub.channel.OAuth1Credential;
import com.beatsbucket.mashub.kitchen.ingredient.twitter.Twitter;

public class TwitterTest {
	
	@Test
	public void testTweet() throws Exception {
		OAuth1Channel channel = getOAuth1Channel();
		
		Twitter twitter = new Twitter();
		twitter.loadChannel(channel);
		TweetAction tweetAction = new TweetAction("tweet");
		twitter.addAction(tweetAction);
		Message message = new Message();
		message.setData("Current Time millisec is " + System.currentTimeMillis());
		Result result = twitter.run(tweetAction, message);
		Assert.assertNotNull(result);
	}

	@Test
	public void testGetTimeline() throws Exception {
		OAuth1Channel channel = getOAuth1Channel();
		
		Twitter twitter = new Twitter();
		twitter.loadChannel(channel);
		TimelineAction timelineAction = new TimelineAction("getTimeLine");
		twitter.addAction(timelineAction);
		//todo this should be json format later
		String targetJson = "3";
		Result result = twitter.observe(timelineAction, targetJson);
		Assert.assertNotNull(result);
		
	}

	private OAuth1Channel getOAuth1Channel() throws MalformedURLException {
		OAuth1Credential credential = new OAuth1Credential(
				"3270752988-Cb1OqGPdgcwqFwi2uwYXg6No3HAr10z2DiL4bSY",
				"gtdRIxhAf73eTX2vfwD2joOVCEiEBBQvCVjQ3Df96AjYb");

		OAuth1Channel channel = new OAuth1Channel(
				"uEZxNR2Ar0IzeK56CL9cWpvqu",
				"nAFc8PYA4KDQdOQYEANJBL9kmtHFd5F1XHB8jLxlQ19YwarT3z",
				credential);

		URL url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=twitterapi&count=1");
		channel.setTestUrl(url);
		return channel;
	}
}
