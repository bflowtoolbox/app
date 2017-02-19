rmdir /q /s repository
mkdir repository
copy src\schema repository
del ..\..\runtime-goalmodel.product\Trac\editing\*.goalmodel*
copy ..\..\runtime-goalmodel.product\Trac\editing\test\*.goalmodel* ..\..\runtime-goalmodel.product\Trac\editing
del ..\..\runtime-goalmodel.product\Trac\rev\*.goalmodel*
copy ..\..\runtime-goalmodel.product\Trac\rev\backup\*.goalmodel* ..\..\runtime-goalmodel.product\Trac\rev