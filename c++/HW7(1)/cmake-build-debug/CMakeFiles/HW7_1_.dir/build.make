# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /home/sasha/clion-2018.2.5/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /home/sasha/clion-2018.2.5/bin/cmake/linux/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/home/sasha/CLionProjects/HW7(1)"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/home/sasha/CLionProjects/HW7(1)/cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/HW7_1_.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/HW7_1_.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/HW7_1_.dir/flags.make

CMakeFiles/HW7_1_.dir/main.cpp.o: CMakeFiles/HW7_1_.dir/flags.make
CMakeFiles/HW7_1_.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/home/sasha/CLionProjects/HW7(1)/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/HW7_1_.dir/main.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/HW7_1_.dir/main.cpp.o -c "/home/sasha/CLionProjects/HW7(1)/main.cpp"

CMakeFiles/HW7_1_.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/HW7_1_.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/home/sasha/CLionProjects/HW7(1)/main.cpp" > CMakeFiles/HW7_1_.dir/main.cpp.i

CMakeFiles/HW7_1_.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/HW7_1_.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/home/sasha/CLionProjects/HW7(1)/main.cpp" -o CMakeFiles/HW7_1_.dir/main.cpp.s

CMakeFiles/HW7_1_.dir/Foo.cpp.o: CMakeFiles/HW7_1_.dir/flags.make
CMakeFiles/HW7_1_.dir/Foo.cpp.o: ../Foo.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/home/sasha/CLionProjects/HW7(1)/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/HW7_1_.dir/Foo.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/HW7_1_.dir/Foo.cpp.o -c "/home/sasha/CLionProjects/HW7(1)/Foo.cpp"

CMakeFiles/HW7_1_.dir/Foo.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/HW7_1_.dir/Foo.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/home/sasha/CLionProjects/HW7(1)/Foo.cpp" > CMakeFiles/HW7_1_.dir/Foo.cpp.i

CMakeFiles/HW7_1_.dir/Foo.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/HW7_1_.dir/Foo.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/home/sasha/CLionProjects/HW7(1)/Foo.cpp" -o CMakeFiles/HW7_1_.dir/Foo.cpp.s

# Object files for target HW7_1_
HW7_1__OBJECTS = \
"CMakeFiles/HW7_1_.dir/main.cpp.o" \
"CMakeFiles/HW7_1_.dir/Foo.cpp.o"

# External object files for target HW7_1_
HW7_1__EXTERNAL_OBJECTS =

HW7_1_: CMakeFiles/HW7_1_.dir/main.cpp.o
HW7_1_: CMakeFiles/HW7_1_.dir/Foo.cpp.o
HW7_1_: CMakeFiles/HW7_1_.dir/build.make
HW7_1_: CMakeFiles/HW7_1_.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="/home/sasha/CLionProjects/HW7(1)/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable HW7_1_"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/HW7_1_.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/HW7_1_.dir/build: HW7_1_

.PHONY : CMakeFiles/HW7_1_.dir/build

CMakeFiles/HW7_1_.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/HW7_1_.dir/cmake_clean.cmake
.PHONY : CMakeFiles/HW7_1_.dir/clean

CMakeFiles/HW7_1_.dir/depend:
	cd "/home/sasha/CLionProjects/HW7(1)/cmake-build-debug" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/home/sasha/CLionProjects/HW7(1)" "/home/sasha/CLionProjects/HW7(1)" "/home/sasha/CLionProjects/HW7(1)/cmake-build-debug" "/home/sasha/CLionProjects/HW7(1)/cmake-build-debug" "/home/sasha/CLionProjects/HW7(1)/cmake-build-debug/CMakeFiles/HW7_1_.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/HW7_1_.dir/depend
