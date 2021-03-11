package com.yan.coupons.exception;

import com.yan.coupons.enums.ErrorType;

@SuppressWarnings("serial")
public class ProgramExceptions extends Exception{
	
		private ErrorType exceptionTreatment;
		
		
		
		public ProgramExceptions(ErrorType exceptions) {
			super();
			this.exceptionTreatment = exceptions;
		}
		
		public ProgramExceptions (Exception e, ErrorType errorType, String message) {
			super(message, e);
			this.exceptionTreatment= errorType;
		}
		
		public ProgramExceptions ( ErrorType errorType, String message) {
			super(message);
			this.exceptionTreatment= errorType;
		}
		
		public ProgramExceptions() {
			super();
		}

		public ErrorType getExceptions() {
			return exceptionTreatment;
		}

		
	
	
	
	
}
