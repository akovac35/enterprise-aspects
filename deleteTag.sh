echo "This script will delete a tag both locally and on github. The following tags are available on the current branch:"
git tag -l
echo
echo "Enter the name of the tag which will be deleted:"
read tagName
echo
echo "Deleting $tagName ..."
set -x
git push --delete origin $tagName
git tag --delete $tagName
set +x
echo
echo "DONE"
