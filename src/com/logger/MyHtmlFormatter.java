package com.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import utils.GenericFunctions;


//This custom formatter formats parts of a log record to a single line
class MyHtmlFormatter extends Formatter {
	// This method is called for every log records
	public String format(LogRecord rec) {
	  StringBuffer buf = new StringBuffer(1000);
	  // Bold any levels >= WARNING
	  buf.append("<tr bgcolor=\"LightYellow\">");
	  
	  buf.append("<td>");
	  buf.append(rec.getSourceClassName());
	  buf.append(' ');
	  buf.append("</td>");
	  
	  buf.append("<td>");
	  buf.append(rec.getSourceMethodName());
	  buf.append(' ');
	  buf.append("</td>");
	  
	  buf.append("<td>");
	  if (rec.getLevel().intValue() == Level.WARNING.intValue()) {
		    buf.append("<b><font color=\"orange\">");
		    buf.append(rec.getLevel());
		    buf.append("</font></b>");
	  } else if (rec.getLevel().intValue() > Level.WARNING.intValue()) {
	    buf.append("<b><font color=\"red\">");
	    buf.append(rec.getLevel());
	    buf.append("</font></b>");
	  } else {
	    buf.append(rec.getLevel());
	  }
	  buf.append("</td>");

	  buf.append("<td>");
	  buf.append(calcDate(rec.getMillis()));
	  buf.append(' ');
	  buf.append("</td>");

	  /* Replacing this with 2 separate columns: step name and log message.
	  buf.append("<td>");
	  buf.append(formatMessage(rec));
	  buf.append('\n');
	  buf.append("</td>");*/

	  
	  if (rec.getMessage().contains(".png")) {		//If screen shot is already taken, then log message as it is.
		  buf.append("<td>");
		  buf.append("&nbsp;");
		  buf.append('\n');
		  buf.append("</td>");
		  buf.append("<td>");
		  buf.append(rec.getMessage()+"&nbsp;");
		  buf.append('\n');
		  buf.append("</td>");
	  } else {
		  buf.append("<td>");
		  String msg = rec.getMessage();
		  String []msg1 = msg.split("::");
		  try {
			  buf.append(msg1[0].trim());
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		  buf.append("&nbsp;");
		  buf.append('\n');
		  buf.append("</td>");
		  
		  buf.append("<td>");
		  try {
			  buf.append(msg1[1].trim()+". ");
		  }catch (Exception e) {
			  //exception comes into steps without log message like entering and exit log message
			  //e.printStackTrace();		  
		  }
		  if (rec.getLevel().intValue() > Level.WARNING.intValue()) {
			String snap = new GenericFunctions().takeScreenShot(msg.trim());
			String msg2 = "<br /><a href=\""+snap+"\">"
	    			+"<img src=\""+snap+"\" alt=\"errorPic\" height=\"50\" width=\"200\"/>"
					+"</a><br />";
			buf.append(msg2);//"<br/> <A href=\""+msg2+"\"> Click here</A> for screenshot. <br/>");
		  }	  
		  buf.append("&nbsp;");
		  buf.append('\n');
		  buf.append("</td>");
	  }
	  
	  buf.append("</tr>");
	  return buf.toString();
	}

	private String calcDate(long millisecs) {
	  SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	  Date resultdate = new Date(millisecs);
	  return date_format.format(resultdate);
	}

	// This method is called just after the handler using this
	// formatter is created
	public String getHead(Handler h) {
	  return "<HTML>\n<HEAD> <FONT face=\"Comic Sans MS\" color=\"black\"> \n" + (new Date()) +"</b>" 
	      + "\n</HEAD>\n<BODY> <FONT face=\"Verdana\">\n<PRE>\n"
	      + "<table width=\"100%\" border=1>\n  "
	      + "<tr bgcolor=\"LightBlue\"><th>Class</th>" +
	      "<th>Method</th>" +
	      "<th>Level</th>" +
	      "<th>Time</th>" +
	      "<th>Step Name</th>" +
	      "<th>Log Message</th>" +
	      "</tr>\n";
	}

	// This method is called just after the handler using this
	// formatter is closed
	public String getTail(Handler h) {
	  return "</table>\n</PRE>\n</FONT>\n</BODY>\n</HTML>\n";
	}

} 