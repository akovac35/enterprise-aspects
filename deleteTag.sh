echo "This script will delete a tag both locally and on github. The following tags are available on the current branch:"
git tag -l
branchName=$(git rev-parse --abbrev-ref HEAD)
echo
echo "Enter the name of the tag which will be deleted:"
read tagName
echo
echo "Deleting $tagName on $branchName ..."
set -x
git push -u origin --delete $tagName
git tag --delete $tagName
set +x
echo
echo "DONE"
