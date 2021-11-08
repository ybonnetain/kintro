echo 'NPM preinstall hook'
echo $(pwd)

cd ../../
./gradlew webKintro:compileProductionExecutableKotlinJs
./gradlew webKintro:publicPackageJson

# make a module for NPM
# this assumes we use IR backend (see tasks bellow)

mkdir ./webKintro/build/dist

# copy module content to dist folder so NPM can install from there
# then copy package.json to executable folder

cp -r ./webKintro/build/compileSync/main/productionExecutable/kotlin/* ./webKintro/build/dist/
cp ./webKintro/build/tmp/publicPackageJson/package.json ./webKintro/build/dist/package.json

# rename all artifacts to index for shorter import

mv ./webKintro/build/dist/kintro-webKintro.js ./webKintro/build/dist/index.js
mv ./webKintro/build/dist/kintro-webKintro.js.map ./webKintro/build/dist/index.js.map
mv ./webKintro/build/dist/kintro-webKintro.d.ts ./webKintro/build/dist/index.d.ts