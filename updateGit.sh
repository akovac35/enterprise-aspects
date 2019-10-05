echo "This script will commit all changes in the current branch and push the updates to origin."
branchName=$(git rev-parse --abbrev-ref HEAD)
echo
echo "Enter commit message:"
read commitMsg
echo
set -x
git add .
git commit -m "$commitMsg"
git push -u origin $branchName
set +x
echo
echo "DONE"
