/*
MIT License

Copyright (c) 2020 ZC

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loglog extends LogException
{
	/**
	 *
	 */
	private static final long serialVersionUID = -9102570949445072576L;

	private Mutex logging_mutex = new Mutex();
	
    private static String logging_file_name = ".\\Logging.log";
	private StringBuffer ostring = new StringBuffer(4096 + 1);

	private Path path;
	private File file;

	private LogLevel MessageLevel;
	private LogVersion VersionLevel;

	public Loglog(final String LogFile2Where, LogVersion version) {
		VersionLevel = version;
		logging_file_name = LogFile2Where;

		new Guard(logging_mutex);

		file = new File(logging_file_name);
		path = file.toPath();
		try {
			Files.writeString(path, "Log4J File Data \r\n", StandardOpenOption.APPEND);
		} catch (IOException e) {
			this.toString();
		}
	}

	public Loglog(final String LogFile2Where) {
		this(LogFile2Where, LogVersion.VersionSTANDARD);
	}

	public Loglog() {
		this(logging_file_name, LogVersion.VersionSTANDARD);
	}

    public final String GetStreamBuffer() {
        new Guard(logging_mutex);
		return ostring.toString();
	}

    private StringBuffer SetLogging(final String data, LogLevel level, LogVersion version) {
		new Guard(logging_mutex);
		
		try{
			ostring.append(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) 				+ " " 	+
				"[" + Thread.currentThread().getStackTrace()[1].getClassName()  + "]" 		+ " " 	+
				"[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]" 		+ " " 	+
				level 																		+ " " 	+
				"[" + Thread.currentThread().getStackTrace()[1].getFileName()   + "]" 		+ " " 	+
				version																		+ " " 	+
				"[" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "]"		+ " - " + 
				data																		+ "   " +
				"[DataHash]" + "{" + FNV.FNV1A_64_HASH(data)					+ "}"		+ " \r\n" 
			);
		} catch(Exception e) {
			System.out.println("The Class Object is fault, please checking Function [getInstance](class) is successful");
			this.toString();
		}
/*			
		ostring.append("=====================================================================================" + "\n");
		ostring.append("【File Name】	: " + Thread.currentThread().getStackTrace()[1].getFileName() + "\n");
		ostring.append("【Class Name】	: " + Thread.currentThread().getStackTrace()[1].getClassName() + "\n");
		ostring.append("【Line Number】	: " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n");
		ostring.append("【Func Name】	: " + Thread.currentThread().getStackTrace()[1].getMethodName() + "\n");
		ostring.append("【Time Now】 	: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");
		ostring.append("【Info Lev】 	: " + level + "\n");
		ostring.append("【Ver Info】 	: " + version + "\n");
		ostring.append("【Data Info】	: " + data + "\n");
		ostring.append("=====================================================================================" + "\n");
*/
		MessageLevel = level;
		VersionLevel = version;

		System.console().writer().println(ostring.toString());
		this.Write2Log();

		return ostring;
	}

	public String Info(final String data) {
		return this.SetLogging(data, LogLevel.LogINFO, VersionLevel).toString();
	}

	public String Warning(final String data) {
		return this.SetLogging(data, LogLevel.LogWARNING, VersionLevel).toString();
	}

	public String Error(final String data) {
		return this.SetLogging(data, LogLevel.LogERROR, VersionLevel).toString();
	}

	public String Critical(final String data) {
		return this.SetLogging(data, LogLevel.LogCRITICAL, VersionLevel).toString();
	}

	public String Allocate(final String data) {
		return this.SetLogging(data, LogLevel.LogALLOCATE, VersionLevel).toString();
	}

	public String System(final String data) {
		return this.SetLogging(data, LogLevel.LogSYSTEM, VersionLevel).toString();
	}

	public String Fatal(final String data) {
		return this.SetLogging(data, LogLevel.LogFATAL, VersionLevel).toString();
	}

	public String Terminated(final String data) {
		return this.SetLogging(data, LogLevel.LogTERMINATED, VersionLevel).toString();
	}

    private void Write2Log() {
		new Guard(logging_mutex);

		try {
			Files.writeString(path, ostring, StandardOpenOption.APPEND);
		} catch(IOException e) {
			this.toString();
		}
	}

    public final String NormalText(final byte[] data) {
		return new String(data);
	}

    public final String HexData(final byte[] data) {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < data.length; i++) {
			sb.append(String.format("%02x", data[i]));
		}

		return sb.toString();
	}

    protected final LogLevel ReportingLevel() {
        return MessageLevel;
    }

    protected final LogVersion ReportingVersion() {
        return VersionLevel;
	}
}
