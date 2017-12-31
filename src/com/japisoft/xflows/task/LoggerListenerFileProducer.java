package com.japisoft.xflows.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.japisoft.framework.toolkit.LoggerListener;

/**
This program is available under two licenses : 

1. For non commercial usage : 

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

2. For commercial usage :

You need to get a commercial license for source usage at : 

http://www.editix.com/buy.html

Copyright (c) 2018 Alexandre Brillant - JAPISOFT SARL - http://www.japisoft.com

@author Alexandre Brillant - abrillant@japisoft.com
@author JAPISOFT SARL - http://www.japisoft.com

*/
public class LoggerListenerFileProducer {

	public LoggerListenerFileProducer( File logFile, LoggerListener listener ) {
		try {
			BufferedReader reader = new BufferedReader( new FileReader( logFile ) );
			try {
				String line = null;
				
				// Read at reverse
				ArrayList l = new ArrayList();
				while ( ( line = reader.readLine() ) != null ) {
					l.add( line );
				}
				
				Collections.reverse( l );

				for ( int i = 0; i < l.size(); i++ ) {
					line = ( String )l.get( i );
					if ( line.startsWith( "**" ) ) {
						listener.addError( line.substring( 2 ) );
					} else
					if ( line.startsWith( "*" ) ) {
						listener.addWarning( line.substring( 1 ) );
					} else {
						listener.addInfo( line );
					}
				}
			} finally {
				reader.close();
			}
		} catch( IOException exc ) {
			listener.addError( exc.getMessage() );
		}
	}

}