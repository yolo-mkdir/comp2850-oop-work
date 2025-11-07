#!/bin/bash
echo "=== Testing Anagram Detector with Infix Notation ==="

echo -e "\nTest 1: Known anagrams (listen/silent)"
echo -e "listen\nsilent" | kotlin AnagramsKt

echo -e "\nTest 2: Known anagrams with spaces (debit card/bad credit)"
echo -e "debit card\nbad credit" | kotlin AnagramsKt

echo -e "\nTest 3: Non-anagrams (hello/world)"
echo -e "hello\nworld" | kotlin AnagramsKt

echo -e "\nTest 4: Same word (test/test)"
echo -e "test\ntest" | kotlin AnagramsKt

echo -e "\nTest 5: Different cases (Listen/Silent)"
echo -e "Listen\nSilent" | kotlin AnagramsKt

echo -e "\nTest 6: With punctuation (evil!?/vile...)"
echo -e "evil!?\nvile..." | kotlin AnagramsKt

echo -e "\nTest 7: Famous anagram (Tom Marvolo Riddle/I am Lord Voldemort)"
echo -e "Tom Marvolo Riddle\nI am Lord Voldemort" | kotlin AnagramsKt
