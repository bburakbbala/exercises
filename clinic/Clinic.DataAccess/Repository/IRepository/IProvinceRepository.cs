using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IProvinceRepository : IRepositoryAsync<Province>
    {
        void Update(Province province);
    }
}