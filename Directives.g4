
lexer grammar Directives;

fragment BYTE_UNIT: ('B' | 'KB' | 'MB' | 'GB' | 'TB');
fragment TIME_UNIT: ('ms' | 's' | 'sec' | 'seconds' | 'm' | 'min' | 'minutes');

BYTE_SIZE: DIGITS BYTE_UNIT;
TIME_DURATION: DIGITS TIME_UNIT;

fragment DIGITS: [0-9]+;
