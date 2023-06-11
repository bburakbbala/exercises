using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ICityRepository : IRepositoryAsync<City>
    {
        void Update(City city);
    }
}