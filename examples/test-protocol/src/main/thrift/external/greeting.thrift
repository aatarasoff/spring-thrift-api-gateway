namespace * example

enum ErrorCode {
    WRONG_LOGIN_OR_PASSWORD = 1
}

exception UnauthorizedException {
    1: required ErrorCode code
}

struct Token {
    1: required string value
}

service TGreetingExternalService {
    string greet(1: Token token)
    throws (
       99: UnauthorizedException ue
    );
}