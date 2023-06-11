using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ICountryOrRegionRepository : IRepositoryAsync<CountryOrRegion>
    {
        void Update(CountryOrRegion countryOrRegion);
    }
}