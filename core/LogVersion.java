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

public enum LogVersion 
{ 
	VersionINTERNAL(0x10000000), 
	VersionALPHA(0x01000000), 
	VersionBETA(0x00100000),
	VersionTEAM(0x00010000), 
	VersionSERVICE_PACK(0x00001000), 
	VersionDEBUG(0x00000100), 
	VersionRELEASE(0x00000010),
	VersionSTANDARD(0xFFFFFFFE);

	private static final LogVersion[] map = new LogVersion[256];
    static {
        for (LogVersion tt : LogVersion.values()) {
            map[tt.code] = tt;
        }
    }

    public static LogVersion of(byte code) {
        return map[0xff & code];
    }

    private int code;

    LogVersion(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public byte toByte() {
        return (byte) code;
    }
}
