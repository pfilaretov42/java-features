## How to run a foreign function with ancient JNI 

### Step 1: Compile the header file

Compile the class with `-h`:

```shell
java-features % javac -h . src/main/java/dev/pfilaretov42/java22/ffm_api/ElvenScrollBefore.java  
```

This produces:
- `src/main/java/dev/pfilaretov42/java22/ffm_api/ElvenScrollBefore.class` class file
- `dev_pfilaretov42_java22_ffm_api_ElvenScrollBefore.h` header file

Move `dev_pfilaretov42_java22_ffm_api_ElvenScrollBefore.h` from the project root to the same package where
`ElvenScrollBefore.java` is located.

### Step 2: Forge the shared library

For MacOS run the following:

```shell
gcc -shared -fpic -o libmordor.dylib \
-I /Users/pfilaretov42/Library/Java/JavaVirtualMachines/liberica-full-24.0.1/include \
-I /Users/pfilaretov42/Library/Java/JavaVirtualMachines/liberica-full-24.0.1/include/darwin \
src/main/java/dev/pfilaretov42/java22/ffm_api/mordor.c
```

This produces `libmordor.dylib` file.

### Step 3: Run it

Run `ElvenScrollBefore` class. It should produce the following output:

```
Runes counted: 24
```
