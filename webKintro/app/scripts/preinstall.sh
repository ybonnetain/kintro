echo 'NPM preinstall hook'
echo $(pwd)

cd ../../
./gradlew webKintro:compileProductionExecutableKotlinJs
./gradlew webKintro:publicPackageJson

# make a module for NPM
# this assumes we use IR backend (see tasks bellow)

mkdir ./build/dist

# copy package.json to executable folder
# then copy module content to dist folder so NPM can install from there

cp ./webKintro/build/tmp/publicPackageJson/package.json ./webKintro/build/compileSync/main/productionExecutable/kotlin/package.json
cp -r ./webKintro/build/compileSync/main/productionExecutable/kotlin ./webKintro/build/dist/
