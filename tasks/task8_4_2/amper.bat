@echo off
echo Amper Project: Sequence Performance Benchmark
echo.

if "%1"=="" (
    echo Usage: %0 run ^<filename^>
    echo Example: %0 run war-and-peace.txt
    exit /b 1
)

if "%1"=="run" (
    if "%2"=="" (
        echo Error: Please provide a filename
        echo Usage: %0 run ^<filename^>
        exit /b 1
    )
    
    if not exist "%2" (
        echo Error: File '%2' not found
        echo Available files:
        dir *.txt 2>nul || echo No text files found
        exit /b 1
    )
    
    echo Compiling and running benchmark...
    kotlinc src/main/kotlin/Benchmark.kt -include-runtime -d benchmark.jar
    java -jar benchmark.jar "%2"
) else (
    echo Unknown command: %1
    echo Available commands: run
    exit /b 1
)
