// Java
#include <jni.h>

// C++
#include <exception>

//////////////////////////////////////////////////////

/// Jni bindings


/// Our custom test exception. Anything "publicly" inheriting std::exception will work
class MyException : public std::exception {
public:
    const char* what() const noexcept override {
        return "This is a really important crash message!";
    }
};

extern "C" JNIEXPORT void JNICALL
Java_com_testfairy_ndkplayground_MainActivity_crashNatively(
        JNIEnv* env,
        jobject /* this */) {
    throw MyException(); // This can be replaced with any foreign function call that throws.
}

//////////////////////////////////////////////////////