package com.snbc.bcvm.reportjar.common;

import android.content.Context;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

public class MySerialPort implements MySerialPortInterface {

	private String m_strPath ;
	private int    m_iRate ;
	private int    m_iflag = (00000400|00004000)  ; //O_NOCTTY(00000400)|O_NDELAY(00004000) ; (00000400|00004000)
	
	private SerialPort m_SerialPort = null ;
	private InputStream m_InputStream = null ;
	private OutputStream m_OutputStream = null ;

	private static int ERROR_SUCCESS = 99;
	private static int ERROR_OPEN_DEVICE = -1001;
	
	private byte[] m_szCmd = new byte[1024];
	private byte[] m_szLog = new byte[1024 * 5];
	
	@Override
	public int OpenSerialPort() {
		// TODO Auto-generated method stub
		if( m_SerialPort == null ) {
			try {
				m_SerialPort   = new SerialPort(new File(m_strPath), m_iRate,m_iflag );
				m_InputStream  = m_SerialPort.getInputStream() ;
				m_OutputStream = m_SerialPort.getOutputStream() ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//MyLogFile.WriteRunErrorLog("MySerialPort", "OpenSerialPort Exception" + e.toString() ) ;
				return ERROR_OPEN_DEVICE;
			}
		}
		return ERROR_SUCCESS;
	}

	@Override
	public int CloseSerialPort()   {
		// TODO Auto-generated method stub
		
		try {
			if( m_SerialPort != null ){
				m_InputStream.close();
				m_InputStream = null ;
				m_OutputStream.close();
				m_OutputStream = null ;
				m_SerialPort.close();
				m_SerialPort = null ;
			}
		} catch (Exception e) {
		}
		return ERROR_SUCCESS;
	}
	@Override
	public int SetParam(String strDevPath, int iParam) {
		// TODO Auto-generated method stub
		m_strPath = strDevPath ;
		m_iRate   = iParam ;
		return ERROR_SUCCESS ;
	}
	@Override
	public int SetParam(Context mContext) {
		return ERROR_SUCCESS ;
	}
	protected boolean ClearRecvBuf(){
		int iRecvLength = 0x00 ;
		int iDataLength = 0x00 ;
		while( ( iRecvLength = RecvCmd(m_szCmd) ) != 0x00 ){
			MyStringFormat.memcpy(m_szLog, iDataLength, m_szCmd, 0, iRecvLength);
			iDataLength += iRecvLength ;
			MyStringFormat.uSleep(10);
		}
		if( iDataLength > 0x00 ) 
		{

			MyStringFormat.uSleep(20);
		}
		return iDataLength > 0x00 ;
	}
	
	private boolean SendCmd( byte[] szCmd , boolean bIsClearBuf ){
		try {
		
			if ( m_OutputStream == null )
			return false ;
		
		    if( bIsClearBuf )
		    ClearRecvBuf();
	
			m_OutputStream.write(szCmd) ;
			
		} catch (Exception e) {

			return false ;
		}
		return true ;
	}
	@Override
	public boolean SendCmd( byte[] szCmd ){
		return  SendCmd(szCmd,true)  ;
	}
	@Override
	public boolean SendCmdNotClear( byte[] szCmd ){
		return SendCmd(szCmd,false) ;
	}
	@Override
	public int RecvCmd( byte[] szCmd ){
		
		int iSize = 0x00 ;
		int iReadDataLength = 0x00 ;
		if ( m_SerialPort == null || m_InputStream == null )
			return iSize ;
		
		try {
			iReadDataLength = m_InputStream.available() ;
			if(  iReadDataLength > 0x00 )
				iSize = m_InputStream.read(szCmd) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block

			return 0x00 ;
		}
		return iSize > 0x00 ? iSize : 0x00 ;
	}
}
