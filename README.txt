Type ant in current directory,
in dist directory: java -jar ElectricityUsage.jar [Command line arguments]
Command line arguments require -r/-g and -w/-d
-r and -w need to be followed by a filename
the files inside dist consist of two valid files, leafIsRoot.txt, sample.txt, 
and sample invalid files
Invalid files
category.txt - invalid power category 
duplicate.txt - duplicate subdivisions entered
format.txt - wrong format
leafComposite.txt - tried to make leaf node a composite node
noComposite.txt - tried to add new node to non existing node
power.txt - invalid power input
twoRoot.txt - two root nodes

build.xml was based on code from Worksheet 1 accessed on the 17 March 2021 


