package com.isuru;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class TwitterClient
{
	public static void main( String[] args )
	{
		// access_secret obtained by authentication user's twitter account
//		String directMessage= "Hi, this is just a test message. " + (int)( Math.random() * 100);
//		String twitterName = "isuru137";// twitter name of the receiver.
//		DirectMessage dm = sendDM( twitterName, directMessage );
//		if( dm != null )
//		{
//			System.out.println(dm);
//		}

		readDms().forEach( System.out::println );
	}
	public static DirectMessage sendDM(String twitterName, String directMessage)
	{
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		try
		{
			return twitter.sendDirectMessage( twitterName, directMessage );
		}
		catch ( TwitterException e )
		{
			e.printStackTrace();
		}
		return null;
	}

	public static List<DM> readDms()
	{
		List<DM> dms = new ArrayList<>(  );
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		try
		{
			ResponseList<DirectMessage> directMessages =  twitter.getDirectMessages();

			directMessages.forEach( directMessage -> {
				DM dm = new DM();
				dm.sender = directMessage.getSenderScreenName();
				dm.message = directMessage.getText();
				dms.add( dm );
			} );
		}
		catch ( TwitterException e )
		{
			e.printStackTrace();
		}

		return dms;
	}

	static class DM
	{
		String sender, message;

		@Override
		public String toString()
		{
			return String.format( "%s - %s", sender, message );
		}
	}
}
