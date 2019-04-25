#~/bin/sh
echo "PWCS in the directory: " $PWD
echo "tag: " $tag
echo "BRANCH_OR_TAG: " $BRANCH_OR_TAG
git pull
if [ $tag != 'origin/master'  ] && [ $tag != 'master' ]; then
#  git checkout tags/$tag
#this is for branch checkout for now
	echo "checkout of" $tag
	git checkout $tag
fi
git pull

echo "=> build application"

echo ant -file build.xml -Ddebug=$debug -DBRANCH_OR_TAG=$BRANCH_OR_TAG -Dtiername=$tiername prod_dist

ant -file build.xml -Ddebug=$debug -DBRANCH_OR_TAG=$BRANCH_OR_TAG -Dtiername=$tiername prod_dist

echo "=> deploying to Tomcat"
mv dist/evssearch.war  /usr/local/apache-tomcat/webapps
echo "done building cchecker-gateway"

echo "entering apache-tomcat directory"
cd /usr/local/apache-tomcat
echo "starting tomcat in foreground"
./bin/catalina.sh run
