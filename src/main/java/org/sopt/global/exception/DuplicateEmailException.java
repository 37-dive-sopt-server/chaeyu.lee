package org.sopt.global.exception;

import org.sopt.global.constant.ErrorMsg;

public class DuplicateEmailException extends RuntimeException {
        public DuplicateEmailException(ErrorMsg errorMsg) {
            super(errorMsg.getMessage());
        }
}
