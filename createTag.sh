echo "This script will create a tag both locally and on github. The following tags are available on the current branch:"
git tag -l
branchName=$(git rev-parse --abbrev-ref HEAD)
echo
echo "Enter the name of the tag which will be created:"
read tagName
echo
echo "Creating $tagName on $branchName ..."
set -x
git tag $tagName
git push -u origin $branchName $tagName
echo
echo "DONE"
