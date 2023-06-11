#pragma once

#include <exception> // exception base class
#include <cstdlib> // std::abort()
class Error : public std::exception {
public:
   Error(const char* mesaj);
   const char* what() const throw() { return mesaj_; }
private:
   const char* mesaj_;
};
